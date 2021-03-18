package id.rllyhz.githubuser.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.rllyhz.githubuser.databinding.AboutMeFragmentBinding
import id.rllyhz.githubuser.databinding.UserDetailFragmentBinding
import id.rllyhz.githubuser.databinding.UserListFragmentBinding

class AboutMeFragment : Fragment() {
    private var _binding: AboutMeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AboutMeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}