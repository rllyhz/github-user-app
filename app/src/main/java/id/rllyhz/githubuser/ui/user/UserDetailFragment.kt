package id.rllyhz.githubuser.ui.user

import android.os.Bundle
import android.util.Log
import android.view.MenuInflater
import android.view.Menu
import android.view.MenuItem
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.rllyhz.githubuser.R
import id.rllyhz.githubuser.databinding.UserDetailFragmentBinding
import id.rllyhz.githubuser.ui.about.AboutMeFragmentDirections
import kotlinx.android.synthetic.main.user_list_fragment.*

class UserDetailFragment : Fragment() {
    private var _binding: UserDetailFragmentBinding? = null
    private val binding get() = _binding!! // like in the documentation approach

    private val args: UserDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UserDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()

        binding.apply {
            args.user.apply {
                mtvUserDetailUsername.text = username
                mtvUserDetailName.text = fullname
                mtvUserDetailCompanyName.text = companyName
                mtvUserDetailRepositoryCount.text = totalRepositories
                mtvUserDetailTotalFollowers.text = totalFollowers
                mtvUserDetailTotalFollowings.text = totalFollowings

                Glide.with(root)
                    .load(avatar)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_launcher_background))
                    .into(sivUserDetailAvatar)
            }
        }

        Log.d("UserDetail", args.user.toString())
    }

    private fun setupToolbar() {
        binding.apply {
            toolbar.inflateMenu(R.menu.main_menu)
            prepareToolbarMenu(toolbar.menu)
            NavigationUI.setupWithNavController(toolbar, findNavController())

            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_item_about_me -> {
                        val action = AboutMeFragmentDirections.actionGlobalAboutMeFragment()
                        findNavController().navigate(action)
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun prepareToolbarMenu(menu: Menu) {
        val searchMenuItem = menu.findItem(R.id.menu_item_search)
        searchMenuItem.isVisible = false
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}