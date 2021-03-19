package id.rllyhz.githubuser.ui.user

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import id.rllyhz.githubuser.R
import id.rllyhz.githubuser.adapter.UserListAdapter
import id.rllyhz.githubuser.databinding.UserListFragmentBinding
import id.rllyhz.githubuser.ui.about.AboutMeFragmentDirections
import id.rllyhz.githubuser.utils.DataUtils

class UserListFragment : Fragment(), SearchView.OnQueryTextListener {
    private var _binding: UserListFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var svFilterUser: SearchView

    private var userListAdapter: UserListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        userListAdapter = UserListAdapter()
        userListAdapter?.setUsers(DataUtils.getUsers(requireContext()))

        userListAdapter?.setOnItemClickCallback {
            val action = UserListFragmentDirections.actionUserListFragmentToUserDetailFragment(it)
            findNavController().navigate(action)
        }
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

        binding.apply {

            rvUserList.apply {
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                adapter = userListAdapter
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        initSvFilterUser(menu.findItem(R.id.menu_item_search))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_about_me -> {
                val action = AboutMeFragmentDirections.actionGlobalAboutMeFragment()
                findNavController().navigate(action)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initSvFilterUser(searchMenuItem: MenuItem) {
        svFilterUser = searchMenuItem.actionView as SearchView
        svFilterUser.setOnQueryTextListener(this)
        svFilterUser.imeOptions = EditorInfo.IME_ACTION_DONE
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        userListAdapter?.filter?.filter(newText)
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        userListAdapter = null
    }
}