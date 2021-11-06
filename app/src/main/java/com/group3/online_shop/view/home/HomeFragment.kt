package com.group3.online_shop.view.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.plusAssign
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.group3.online_shop.R
import com.group3.online_shop.databinding.FragmentHomeBinding
import com.group3.online_shop.navigation.KeepStateNavigator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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


    }



}