package com.example.androidapptemplate.features.webapi.trivia.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidapptemplate.R
import com.example.androidapptemplate.core.util.ToastHelper
import com.example.androidapptemplate.core.util.viewBindings
import com.example.androidapptemplate.databinding.FragmentTriviaHistoryBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class TriviaHistoryFragment : Fragment(R.layout.fragment_trivia_history) {
    private val binding by viewBindings(FragmentTriviaHistoryBinding::bind)
    private val viewModel by viewModels<TriviaHistoryViewModel>()
    private val adapter = TriviaHistoryListAdapter()

    @Inject
    lateinit var toastHelper: ToastHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
        }
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.uistate.observe(viewLifecycleOwner, {
            when (it) {
                is UiState.Error -> {
                    toastHelper.showToast(it.throwable.localizedMessage ?: "error")
                }
                is UiState.Success -> {
                    adapter.submitList(it.data)
                }
            }
        })
    }
}