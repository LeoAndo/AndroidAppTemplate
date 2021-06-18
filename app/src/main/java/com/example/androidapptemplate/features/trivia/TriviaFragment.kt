package com.example.androidapptemplate.features.trivia

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidapptemplate.R
import com.example.androidapptemplate.databinding.FragmentTriviaBinding
import com.example.androidapptemplate.features.core.dialog.OnRetryConnectionListener
import com.example.androidapptemplate.util.ToastHelper
import com.example.androidapptemplate.util.viewBindings
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
internal class TriviaFragment : Fragment(R.layout.fragment_trivia) {
    private val binding by viewBindings(FragmentTriviaBinding::bind)
    private val viewModel by viewModels<TriviaViewModel>()
    private val exceptionHandler =
        TriviaExceptionHandler(fragment = this, onUnAuthorizedAction = {})

    @Inject
    lateinit var toastHelper: ToastHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonRandomTrivia.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch(exceptionHandler.coroutineExceptionHandler) {
                viewModel.getRandomTrivia()
            }
        }
        exceptionHandler.setOnRetryConnectionListener(object : OnRetryConnectionListener {
            override fun onRetry() {
                viewLifecycleOwner.lifecycleScope.launch(exceptionHandler.coroutineExceptionHandler) {
                    viewModel.getRandomTrivia()
                }
            }
        })
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.result.observe(viewLifecycleOwner, { toastHelper.showToast(it) })
    }
}