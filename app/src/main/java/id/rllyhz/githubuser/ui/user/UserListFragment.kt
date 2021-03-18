package id.rllyhz.githubuser.ui.user

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import id.rllyhz.githubuser.R
import id.rllyhz.githubuser.databinding.UserListFragmentBinding
import kotlinx.android.synthetic.main.user_list_fragment.*

class UserListFragment : Fragment() {
    private var _binding: UserListFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        _binding = UserListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_about_me -> {
                val action = UserListFragmentDirections.actionUserListFragmentToAboutMeFragment()
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