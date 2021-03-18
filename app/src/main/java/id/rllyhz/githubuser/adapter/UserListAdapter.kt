package id.rllyhz.githubuser.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.rllyhz.githubuser.data.User
import id.rllyhz.githubuser.databinding.ItemUserBinding

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {

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
                    .into(ivItemUserAvatar)

                tvItemUserName.text = user.fullname
                tvItemUserUsername.text = user.username
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

    val differ = AsyncListDiffer(this, differCallback)

    // OnItemClick interface
    interface OnItemClickListener {
        fun onItemClick(user: User)
    }

    private var onItemClickCallback: OnItemClickListener? = null

    fun setOnItemClickCallback(callback: OnItemClickListener) {
        onItemClickCallback = callback
    }
}