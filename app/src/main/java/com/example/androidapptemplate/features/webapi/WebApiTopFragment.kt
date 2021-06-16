package com.example.androidapptemplate.features.webapi

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidapptemplate.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class WebApiTopFragment : Fragment(R.layout.web_api_top_fragment) {
    private val viewModel by viewModels<WebApiTopViewModel>()
}