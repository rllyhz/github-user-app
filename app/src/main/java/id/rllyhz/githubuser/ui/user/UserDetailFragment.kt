package id.rllyhz.githubuser.ui.user

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import id.rllyhz.githubuser.R
import id.rllyhz.githubuser.databinding.UserDetailFragmentBinding
import id.rllyhz.githubuser.ui.about.AboutMeFragmentDirections

class UserDetailFragment : Fragment() {
    private var _binding: UserDetailFragmentBinding? = null
    private val binding get() = _binding!!

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
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        _binding = UserDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            args.user.apply {
                tvUserDetailName.text = fullname
            }
        }

        Log.d("UserDetail", args.user.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)

        val searchMenuItem = menu.findItem(R.id.menu_item_search)
        searchMenuItem.isVisible = false

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                findNavController().navigateUp()
                true
            }
            R.id.menu_item_about_me -> {
                val action = AboutMeFragmentDirections.actionGlobalAboutMeFragment()
                findNavController().navigate(action)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}