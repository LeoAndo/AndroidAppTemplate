package com.example.androidapptemplate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
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
        setupNavController()
        observeDestination()
    }

    private fun observeDestination() {
        navController.addOnDestinationChangedListener { n, destination, _ ->
            setupNavController(destination.id)
        }
    }

    private fun setupNavController() {
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.home_dest,
                R.id.algorithm_top_dest,
                R.id.web_api_top_dest,
                R.id.google_api_top_dest
            ),
            // fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )
        // this.setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.inflateMenu(R.menu.menu_main)
        binding.navView.setupWithNavController(navController)
    }

    private fun setupNavController(destinationId: Int) {
        when (destinationId) {
            R.id.splash_dest, R.id.login_dest -> {
                binding.navView.setVisibleOrGone(false)
            }
            else -> {
                binding.navView.setVisibleOrGone(true)
            }
        }
    }
}