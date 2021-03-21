package id.rllyhz.githubuser.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.rllyhz.githubuser.R
import id.rllyhz.githubuser.data.User
import id.rllyhz.githubuser.databinding.ItemUserBinding
import java.util.*

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserListViewHolder>(), Filterable {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(differ.currentList[position], holder.itemView.context)
    }

    override fun getItemCount() = differ.currentList.size

    // ViewHolder
    inner class UserListViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User, context: Context) {
            binding.apply {
                Glide.with(context)
                    .load(user.avatar)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_launcher_background))
                    .apply(RequestOptions().centerCrop())
                    .into(ivItemUserAvatar)

                tvItemUserName.text = user.fullname
                tvItemUserUsername.text = user.username
                mtvItemUserCompanyName.text = user.companyName

                // set unique transition name for each
                ivItemUserAvatar.transitionName =
                    "${itemView.context.getString(R.string.transition_name_item_user_avatar)}_$adapterPosition"
                tvItemUserName.transitionName =
                    "${itemView.context.getString(R.string.transition_name_item_user_name)}_$adapterPosition"

                // create the extras for sharedElement
                val extrasForSharedElement = FragmentNavigatorExtras(
                    ivItemUserAvatar to itemView.context.getString(R.string.transition_name_item_user_avatar),
                    tvItemUserName to itemView.context.getString(R.string.transition_name_item_user_name)
                )

                itemView.setOnClickListener {
                    onItemClickCallback?.let { it(user, extrasForSharedElement) }
                }
            }
        }
    }


    // Callback for DifferUtil
    private val differCallback = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.username == newItem.username
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)
    private val allUsers: MutableList<User> = mutableListOf() // for copy

    fun setUsers(users: MutableList<User>) {
        allUsers.clear()
        allUsers.addAll(users)
        differ.submitList(allUsers)
    }


    // OnItemClick callback
    private var onItemClickCallback: ((User, FragmentNavigator.Extras) -> Unit)? = null

    fun setOnItemClickCallback(callback: ((User, FragmentNavigator.Extras) -> Unit)) {
        onItemClickCallback = callback
    }


    // Filtering
    private val filtering: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredUsers: MutableList<User> = mutableListOf()

            if (constraint == null || constraint.isEmpty()) {
                filteredUsers.addAll(allUsers)
            } else {
                val filterPattern = constraint.toString().toLowerCase(Locale.ROOT).trim()

                for (item in allUsers) {
                    if (item.username.toLowerCase(Locale.ROOT).contains(filterPattern) ||
                        item.fullname.toLowerCase(Locale.ROOT).contains(filterPattern)
                    ) {
                        filteredUsers.add(item)
                    }
                }
            }

            val results = FilterResults()
            results.values = filteredUsers
            return results
        }

        override fun publishResults(p0: CharSequence?, results: FilterResults?) {
            val users = results?.values as List<User>
            differ.submitList(users)
        }
    }

    override fun getFilter() = filtering
}