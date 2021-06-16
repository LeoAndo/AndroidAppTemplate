package com.example.androidapptemplate.features.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidapptemplate.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class SplashFragment : Fragment(R.layout.fragment_splash) {
    private val viewModel by viewModels<SplashViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.finishSplash.observe(viewLifecycleOwner, {
            findNavController().navigate(SplashFragmentDirections.goToLoginAction())
        })
        viewModel.initialize()
    }
}