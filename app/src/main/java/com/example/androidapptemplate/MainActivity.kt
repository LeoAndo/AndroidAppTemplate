package com.example.androidapptemplate

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
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

        setSupportActionBar(binding.toolbar)
        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = host.navController

        setupNavController()
        observeDestination()
    }

    private fun observeDestination() {
        // 画面遷移の監視
        navController.addOnDestinationChangedListener { _, destination, _ ->
            Log.d("aaaa", "destination.id: " + destination.id)
            setupNavController(destination.id)
            setupToolBar(destination.id)
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
            //fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )
        this.setupActionBarWithNavController(navController, appBarConfiguration)
        NavigationUI.setupWithNavController(binding.toolbar, navController)
        binding.navView.inflateMenu(R.menu.menu_main)
        binding.navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val ret = super.onSupportNavigateUp()
        Log.d("aaa", "onSupportNavigateUp ret: " + ret)
        return ret
    }

    private fun setupNavController(destinationId: Int) {
        when (destinationId) {
            R.id.home_dest, R.id.algorithm_top_dest, R.id.web_api_top_dest, R.id.google_api_top_dest -> {
                binding.navView.setVisibleOrGone(true)
            }
            else -> { binding.navView.setVisibleOrGone(false) }
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