package com.example.androidapptemplate.features.webapi.trivia.select

import android.os.Bundle
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidapptemplate.R
import com.example.androidapptemplate.core.util.ToastHelper
import com.example.androidapptemplate.core.util.handleNetworkConnectionError
import com.example.androidapptemplate.core.util.viewBindings
import com.example.androidapptemplate.databinding.FragmentTriviaSelectBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class TriviaSelectFragment : Fragment(R.layout.fragment_trivia_select) {
    private val binding by viewBindings(FragmentTriviaSelectBinding::bind)
    private val viewModel by viewModels<TriviaSelectViewModel>()

    @Inject
    lateinit var toastHelper: ToastHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.let {
            it.viewModel = viewModel
            it.buttonGetTrivia.setOnClickListener {
                getTrivia()
            }
        }

        viewModel.uistate.observe(viewLifecycleOwner) {
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
        }
    }

    private fun getTrivia() {
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