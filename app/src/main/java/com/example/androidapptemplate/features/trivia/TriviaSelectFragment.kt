package com.example.androidapptemplate.features.trivia

import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidapptemplate.R
import com.example.androidapptemplate.databinding.FragmentTriviaSelectBinding
import com.example.androidapptemplate.features.core.dialog.OnRetryConnectionListener
import com.example.androidapptemplate.util.ToastHelper
import com.example.androidapptemplate.util.viewBindings
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
internal class TriviaSelectFragment : Fragment(R.layout.fragment_trivia_select) {
    private val binding by viewBindings(FragmentTriviaSelectBinding::bind)
    private val viewModel by viewModels<TriviaSelectViewModel>()
    private val exceptionHandler =
        TriviaExceptionHandler(fragment = this, onUnAuthorizedAction = {})

    @Inject
    lateinit var toastHelper: ToastHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.let {
            it.viewModel = viewModel
            it.buttonGetTrivia.setOnClickListener {
                viewLifecycleOwner.lifecycleScope.launch(exceptionHandler.coroutineExceptionHandler) {
                    getTrivia()
                }
            }

        }
        exceptionHandler.setOnRetryConnectionListener(object : OnRetryConnectionListener {
            override fun onRetry() {
                viewLifecycleOwner.lifecycleScope.launch(exceptionHandler.coroutineExceptionHandler) {
                    getTrivia()
                }
            }
        })
    }

    private suspend fun getTrivia() {
        if (validate()) {
            viewModel.getTrivia()
        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.trivia_error_message),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun validate(): Boolean {
        var validated = true
        val filledDateDropdown =
            binding.root.findViewById<AutoCompleteTextView>(R.id.filled_date_dropdown)
        val filledMonthDropdown =
            binding.root.findViewById<AutoCompleteTextView>(R.id.filled_month_dropdown)

        if (filledMonthDropdown.text.toString().isEmpty()
            && filledDateDropdown.text.toString().isEmpty()
        ) {
            validated = false
        }
        return validated
    }
}