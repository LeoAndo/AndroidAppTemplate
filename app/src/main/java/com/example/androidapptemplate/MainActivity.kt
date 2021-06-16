package com.example.androidapptemplate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.androidapptemplate.databinding.ActivityMainBinding
import com.example.androidapptemplate.util.setVisibleOrGone
import com.example.androidapptemplate.util.viewBindings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private val binding by viewBindings(ActivityMainBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = host.navController

        setupToolBar()
        setupBottomBar()
        observeDestination();
    }

    private fun observeDestination() {
        // 画面遷移の監視
        navController.addOnDestinationChangedListener { _, destination, _ ->
            setupTheme(destination.id)
            setupBottomBar(destination.id)
            setupToolBar(destination.id)
        }
    }

    private fun setupToolBar() {
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { findNavController(R.id.nav_host_fragment).popBackStack() }
    }

    private fun setupBottomBar() {
        val topLevelDestinationIds =
            setOf(R.id.home_dest, R.id.algorithm_top_dest, R.id.web_api_top_dest, R.id.google_api_top_dest)
        val appBarConfiguration = AppBarConfiguration(topLevelDestinationIds)
        binding.navView.inflateMenu(R.menu.menu_main)

        NavigationUI.setupActionBarWithNavController(
            this,
            navController,
            appBarConfiguration
        )
        binding.navView.setupWithNavController(navController)
    }

    private fun setupTheme(destinationId: Int) {
        when (destinationId) {
            R.id.splash_dest, R.id.login_dest -> {
                setTheme(R.style.Theme_AndroidAppTemplate_NoActionBar)
            }
            else -> setTheme(R.style.Theme_AndroidAppTemplate)
        }
    }

    private fun setupBottomBar(destinationId: Int) {
        when (destinationId) {
            R.id.home_dest, R.id.algorithm_top_dest, R.id.web_api_top_dest, R.id.google_api_top_dest -> {
                binding.navView.setVisibleOrGone(true)
            }
            else -> binding.navView.setVisibleOrGone(false)
        }
    }

    private fun setupToolBar(destinationId: Int) {
        when (destinationId) {
            R.id.splash_dest, R.id.login_dest -> supportActionBar?.hide()
            else -> {
                supportActionBar?.show()
            }
        }
    }
}