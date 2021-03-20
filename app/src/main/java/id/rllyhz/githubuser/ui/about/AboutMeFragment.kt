package id.rllyhz.githubuser.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import id.rllyhz.githubuser.R
import id.rllyhz.githubuser.databinding.AboutMeFragmentBinding
import kotlinx.android.synthetic.main.user_list_fragment.*

class AboutMeFragment : Fragment() {
    private var _binding: AboutMeFragmentBinding? = null
    private val binding get() = _binding!! // like in the documentation approach

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AboutMeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
    }

    private fun setupToolbar() {
        NavigationUI.setupWithNavController(toolbar, findNavController())
    }
}