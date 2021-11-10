package com.group6.onlineshop.view.profile

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.group6.onlineshop.R
import com.group6.onlineshop.data.SharedPreferenceHelper
import com.group6.onlineshop.data.component.Loading
import com.group6.onlineshop.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()
    private val sharedPreferences by lazy { SharedPreferenceHelper.getInstance(requireContext()) }
    private val loading = Loading()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nestedNavHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = nestedNavHostFragment.navController
        initViewAction(navController)
    }

    @SuppressLint("SetTextI18n")
    private fun initViewAction(navController: NavController) {
        binding.userTitle.setText("User Information")

        binding.backButton.setOnClickListener {
            navController.navigate(R.id.homeFragment)
        }

        viewModel.getProfile("Bearer " + sharedPreferences.getAccessToken())

        observe()
        showKeyBoard()
        initAction()
    }

    private fun initAction() {
        binding.updateButton.setOnClickListener {
            clickUpdateButton()
        }
    }

    private fun observe() {

        viewModel.profile.observe(viewLifecycleOwner, { profile ->
            binding.username.setText(profile.name)
            binding.email.text = profile.email
            binding.phone.text = profile.phone
            Glide.with(this).load(profile.image)
                .centerInside()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(binding.profileImageView)
        })

    }

    private fun clickUpdateButton() {
        loading.show(childFragmentManager, "loading_dialog")
    }

    private fun showKeyBoard() {
        binding.usernameLayout.setOnClickListener {
            binding.username.requestFocus()
            binding.username.setSelection(binding.username.text.toString().length)
            val keyboard =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            keyboard.showSoftInput(binding.username, 0)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}