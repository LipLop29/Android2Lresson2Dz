package com.example.android2lresson2dz.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.android2lresson2dz.R
import com.example.android2lresson2dz.utils.PreferenceHelper

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        if (PreferenceHelper.onBoard && PreferenceHelper.signIn) {
            navController.navigate(R.id.noteAppFragment)
        } else if (PreferenceHelper.onBoard) {
            navController.navigate(R.id.signInFragment)
        }
        else if (PreferenceHelper.signIn){
            navController.navigate(R.id.noteAppFragment)
        }else {
            navController.navigate(R.id.onBoardFragment)
        }
    }
}