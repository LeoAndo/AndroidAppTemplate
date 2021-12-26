package com.example.androidapptemplate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.androidapptemplate.core.util.viewBindings
import com.example.androidapptemplate.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private val binding by viewBindings(ActivityMainBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initNavController()
        observeDestination()
    }

    private fun observeDestination() {
        navController.addOnDestinationChangedListener { n, destination, _ ->
            updateContent(destination.id)
        }
    }

    private fun initNavController() {
        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = host.navController
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.home_dest,
                R.id.algorithm_top_dest,
                R.id.web_api_top_dest,
                R.id.play_ground_top_dest
            ),
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        this.setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.inflateMenu(R.menu.menu_main)
        binding.navView.setupWithNavController(navController)
    }

    private fun updateContent(destinationId: Int) {
        when (destinationId) {
            R.id.splash_dest, R.id.login_dest -> {
                supportActionBar?.hide()
                binding.navView.isVisible = false
            }
            R.id.home_dest, R.id.algorithm_top_dest, R.id.web_api_top_dest, R.id.play_ground_top_dest -> { // topLevelDestinationIds
                supportActionBar?.hide()
                binding.navView.isVisible = true
            }
            else -> {
                supportActionBar?.show()
                binding.navView.isVisible = false
            }
        }
    }
}