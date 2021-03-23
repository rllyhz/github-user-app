package id.rllyhz.githubuser.ui.about

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
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

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()

        binding.apply {
            Glide.with(requireContext())
                .load(requireContext().getDrawable(R.drawable.image_creator))
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions().centerCrop())
                .into(ivAboutMeCreatorAvatar)

            fabAboutMeLikeBtn.setOnClickListener { showToast() }
            cardviewAboutMeLinkedin.setOnClickListener { openLink(requireContext().getString(R.string.linkedin_link)) }
            cardviewAboutMeGithub.setOnClickListener { openLink(requireContext().getString(R.string.github_link)) }
            cardviewAboutMeInstagram.setOnClickListener { openLink(requireContext().getString(R.string.instagram_link)) }
        }
    }

    private fun setupToolbar() {
        NavigationUI.setupWithNavController(toolbar, findNavController())
    }

    private fun openLink(url: String) {
        (requireActivity() as AppCompatActivity)
            .startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    private fun showToast() {
        Toast.makeText(requireContext(), "Thank you!", Toast.LENGTH_SHORT).show()
    }
}