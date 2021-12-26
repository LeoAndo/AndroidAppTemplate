package com.example.androidapptemplate.features.webapi.trivia.random

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidapptemplate.R
import com.example.androidapptemplate.core.util.ToastHelper
import com.example.androidapptemplate.core.util.handleNetworkConnectionError
import com.example.androidapptemplate.core.util.viewBindings
import com.example.androidapptemplate.databinding.FragmentTriviaRandomBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class TriviaRandomFragment : Fragment(R.layout.fragment_trivia_random) {
    private val binding by viewBindings(FragmentTriviaRandomBinding::bind)
    private val viewModel by viewModels<TriviaRandomViewModel>()

    @Inject
    lateinit var toastHelper: ToastHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.let {
            it.buttonGetRandomDateTrivia.setOnClickListener {
                viewModel.getRandomDateTrivia()
            }
            it.buttonGetRandomMonthTrivia.setOnClickListener {
                viewModel.getRandomMonthTrivia()
            }
            it.buttonGetRandomTrivia.setOnClickListener {
                viewModel.getRandomTrivia()
            }
            it.buttonGetRandomYearTrivia.setOnClickListener {
                viewModel.getRandomYearTrivia()
            }
        }
        viewModel.uistate.observe(viewLifecycleOwner, {
            when (it) {
                is UiState.Error -> {
                    binding.progressIndicatorLayout.hide()
                    handleNetworkConnectionError(it.throwable, onRetry = {})
                }
                UiState.Loading -> {
                    binding.progressIndicatorLayout.show()
                }
                is UiState.Success -> {
                    binding.progressIndicatorLayout.hide()
                    binding.textResultTrivia.text = it.data
                }
            }
        })
    }
}