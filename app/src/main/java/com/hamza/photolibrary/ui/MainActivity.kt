package com.hamza.photolibrary.ui

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.hamza.photolibrary.R
import com.hamza.photolibrary.base.BaseActivity
import com.hamza.photolibrary.databinding.MainActivityBinding

class MainActivity : BaseActivity() {

    lateinit var bi: MainActivityBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = MainActivityBinding.inflate(layoutInflater)
        setContentView(bi.root)

        setupViews()
    }

    private fun setupViews() {
        // Navigation
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostMain) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}