package com.group6.online_shop.view.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.plusAssign
import androidx.navigation.ui.setupWithNavController
import com.group6.online_shop.R
import com.group6.online_shop.data.SharedPreferenceHelper
import com.group6.online_shop.databinding.FragmentHomeBinding
import com.group6.online_shop.navigation.KeepStateNavigator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val sharedPreferences by lazy { SharedPreferenceHelper.getInstance(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navigator = KeepStateNavigator(
            requireContext(),
            navHostFragment.childFragmentManager,
            R.id.navHostFragment
        )
        val navController = navHostFragment.navController
        navController.navigatorProvider += navigator
        navController.setGraph(R.navigation.home_nav_graph)

        binding.bottomNavigationView.setupWithNavController(navController)
        binding.navView.setupWithNavController(navController)

        logOut(navController)

    }

    private fun logOut(navController: NavController) {
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.profileFragment -> {
                    navController.navigate(R.id.profileFragment)
                    true
                }
                R.id.logout -> {
                    logOutDialog()
                    true
                }

                else -> {
                    false
                }
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun logOutDialog() {
        val dialog = AlertDialog.Builder(context).apply {
            setTitle("Information")
            setMessage("Are you want to log out?")
            setCancelable(false)
            setPositiveButton("Yes") { dialog, id ->
                clearAccessToken()
                dialog.dismiss()
            }
            setNegativeButton("No") { dialog, id ->
                dialog.cancel()
            }
        }.create()
        try {
            dialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun clearAccessToken() {
        sharedPreferences.setAccessToken("")
        findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
    }


}