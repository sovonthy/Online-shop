package com.group3.online_shop.view.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.group3.online_shop.R
import com.group3.online_shop.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        initViewAction(navController)
    }

    private fun initViewAction(navController: NavController){
        binding.loginButton.setOnClickListener {
            clickLoginButton()
            navController.navigate(R.id.action_loginFragment_to_homeFragment)
        }
       binding.signUpTextView.setOnClickListener {
           navController.navigate(R.id.action_loginFragment_to_signUpFragment)
       }
    }

    private fun clickLoginButton(){
        viewModel.login(
            email = "virakcambodia44@gmail.com",
            password = "@password@",
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}