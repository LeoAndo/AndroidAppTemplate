package com.example.androidapptemplate.features.trivia

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidapptemplate.R
import com.example.androidapptemplate.databinding.FragmentTriviaHistoryBinding
import com.example.androidapptemplate.util.ToastHelper
import com.example.androidapptemplate.util.viewBindings
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class TriviaHistoryFragment : Fragment(R.layout.fragment_trivia_history) {
    private val binding by viewBindings(FragmentTriviaHistoryBinding::bind)
    private val viewModel by viewModels<TriviaHistoryViewModel>()
    private val exceptionHandler = TriviaHistoryExceptionHandler(fragment = this)
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
        viewModel.resultTrivia.observe(viewLifecycleOwner, { adapter.submitList(it) })
        viewModel.failure.observe(viewLifecycleOwner, { toastHelper.showToast(it) })
    }
}