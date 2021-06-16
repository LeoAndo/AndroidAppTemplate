package com.example.androidapptemplate.features.googleapi

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidapptemplate.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class GoogleApiTopFragment : Fragment(R.layout.google_api_top_fragment) {
    private val viewModel by viewModels<GoogleApiTopViewModel>()
}