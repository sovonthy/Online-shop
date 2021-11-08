package com.group6.online_shop

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.group6.online_shop.data.SharedPreferenceHelper

class MainActivity : AppCompatActivity() {

    private val sharedPreferences by lazy { SharedPreferenceHelper.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        getNavigate(navController = navHostFragment.navController)
    }

    private fun getNavigate(navController: NavController){
        if (SharedPreferenceHelper.getInstance(this).getAccessToken() != "") {
            navController.navigate(R.id.homeFragment)
        } else {
                navController.navigate(R.id.loginFragment)
        }
    }

}