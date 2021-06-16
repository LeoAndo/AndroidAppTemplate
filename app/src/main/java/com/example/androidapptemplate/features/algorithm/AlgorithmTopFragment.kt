package com.example.androidapptemplate.features.algorithm

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidapptemplate.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class AlgorithmTopFragment : Fragment(R.layout.algorithm_top_fragment) {
    private val viewModel by viewModels<AlgorithmTopViewModel>()
}