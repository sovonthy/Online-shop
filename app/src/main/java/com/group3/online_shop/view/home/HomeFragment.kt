package com.group3.online_shop.view.home

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.plusAssign
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.group3.online_shop.R
import com.group3.online_shop.databinding.FragmentHomeBinding
import com.group3.online_shop.databinding.FragmentLoginBinding
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
        val navHostFragment = childFragmentManager.findFragmentById(R.id.home_fragment_container)!!
        val navigator = KeepStateNavigator(requireContext(), navHostFragment.childFragmentManager, R.id.home_fragment_container)
        val navController = navHostFragment.findNavController()
        navController.navigatorProvider += navigator
        navController.setGraph(R.navigation.home_nav_graph)
        initBottomNavigationView(navController)
    }

    @SuppressLint("RestrictedApi")
    private fun initBottomNavigationView(navController: NavController) {
        binding.bottomNavigationView.setupWithNavController(navController = navController)
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            try {
                when (item.itemId) {
//                    R.id.cardFragment -> {
//                        Navigation.findNavController(requireActivity(), R.id.notification_fragment_container).navigateUp()
//                    }
//                    R.id.wishListFragment -> {
//                        Navigation.findNavController(requireActivity(), R.id.history_fragment_container).navigateUp()
//                    }
//                    R.id.profileFragment -> {
//                        Navigation.findNavController(requireActivity(), R.id.history_fragment_container).navigateUp()
//                    }
                }
            } catch (e: Exception) {
            }
            NavigationUI.onNavDestinationSelected(
                item,
                Navigation.findNavController(requireActivity(), R.id.home_fragment_container)
            )
        }
    }


}