package com.group3.online_shop.view.login

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.group3.online_shop.R
import com.group3.online_shop.data.component.Loading
import com.group3.online_shop.data.component.ValidationDialog
import com.group3.online_shop.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()
    private val dialog = Loading()

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
        initObserve(navController)
    }

    private fun initViewAction(navController: NavController) {
        binding.loginButton.setOnClickListener {
            clickLoginButton()
        }
        binding.signUpTextView.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }

    private fun initObserve(navController: NavController) {
        viewModel.login.observe(viewLifecycleOwner, { login ->
            if (login != null) {
                navController.navigate(R.id.action_loginFragment_to_homeFragment)
            }
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, { message ->
            if (message != null) {
                if (dialog.isVisible) {
                    dialog.dismiss()
                    if (message == "400") {
                        val dialog = AlertDialog.Builder(context).apply {
                            setTitle("Information")
                            setMessage("The email address or password is not correct.")
                            setCancelable(false)
                            setPositiveButton("OK") { dialog, id ->
                                dialog.dismiss()
                            }
                        }.create()
                        try {
                            dialog.show()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    } else {
                        val dialog = AlertDialog.Builder(context).apply {
                            setTitle("Information")
                            setMessage(message)
                            setCancelable(false)
                            setPositiveButton("OK") { dialog, id ->
                                dialog.dismiss()
                            }
                        }.create()
                        try {
                            dialog.show()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }

                }
            }
        })
    }

    private fun clickLoginButton() {
        dialog.show(childFragmentManager, "loading_dialog")
        viewModel.login(
            email = binding.emailTextInput.text.toString(),
            password = binding.passwordTextInput.text.toString(),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}