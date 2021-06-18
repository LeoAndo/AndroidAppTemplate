package com.example.androidapptemplate.features.trivia

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidapptemplate.R
import com.example.androidapptemplate.databinding.FragmentTriviaRandomBinding
import com.example.androidapptemplate.features.core.dialog.*
import com.example.androidapptemplate.util.ToastHelper
import com.example.androidapptemplate.util.viewBindings
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
internal class TriviaRandomFragment : Fragment(R.layout.fragment_trivia_random) {
    private val binding by viewBindings(FragmentTriviaRandomBinding::bind)
    private val viewModel by viewModels<TriviaRandomViewModel>()
    private val exceptionHandler =
        TriviaExceptionHandler(fragment = this, onUnAuthorizedAction = {})

    @Inject
    lateinit var toastHelper: ToastHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.let {
            it.viewModel = viewModel
            it.buttonGetRandomDateTrivia.setOnClickListener {
                apiCall { viewModel.getRandomDateTrivia() }
            }
            it.buttonGetRandomMonthTrivia.setOnClickListener {
                apiCall { viewModel.getRandomMonthTrivia() }
            }
            it.buttonGetRandomTrivia.setOnClickListener {
                apiCall { viewModel.getRandomTrivia() }
            }
            it.buttonGetRandomYearTrivia.setOnClickListener {
                apiCall { viewModel.getRandomYearTrivia() }
            }
        }
        exceptionHandler.setOnRetryConnectionListener(object : OnRetryConnectionListener {
            override fun onRetry() {
                viewLifecycleOwner.lifecycleScope.launch(exceptionHandler.coroutineExceptionHandler) {
                    when (viewModel.retryState) {
                        RetryState1 -> viewModel.getRandomMonthTrivia()
                        RetryState2 -> viewModel.getRandomTrivia()
                        RetryState3 -> viewModel.getRandomYearTrivia()
                        RetryState4 -> viewModel.getRandomDateTrivia()
                        else -> Log.w("TriviaRandomFragment", "unhandled retryState: ${viewModel.retryState}")
                    }
                }
            }
        })
    }

    private fun <T> apiCall(apiCall: suspend () -> T) {
        // 特定のスコープの全てのジョブを明示的にキャンセルする場合は、以下で良さそう.
        // viewModelScope.coroutineContext.cancelChildren()
        viewLifecycleOwner.lifecycleScope.launch(exceptionHandler.coroutineExceptionHandler) {
            apiCall.invoke()
        }
    }
}