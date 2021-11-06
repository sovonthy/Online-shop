package com.group3.online_shop.view.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.group3.online_shop.R
import com.group3.online_shop.data.SharedPreferenceHelper
import com.group3.online_shop.databinding.FragmentProfileBinding
import com.group3.online_shop.view.login.LoginFragmentDirections
import com.group3.online_shop.view.login.LoginViewModel

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()
    private val sharedPreferences by lazy { SharedPreferenceHelper.getInstance(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewAction()
    }

    private fun initViewAction(){
        binding.userTitle.setText("User Information")

        viewModel.getProfile("Bearer " + sharedPreferences.getAccessToken())

        observe()
    }

    private fun observe(){
        viewModel.profile.observe(viewLifecycleOwner, { profile ->
           binding.username.text = profile.name
            binding.email.text = profile.email
            binding.phone.text = profile.phone
            Glide.with(this).load(profile.image)
                .centerInside()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(binding.profileImageView)

        })

    }

}