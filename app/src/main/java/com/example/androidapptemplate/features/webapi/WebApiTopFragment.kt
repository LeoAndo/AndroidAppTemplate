package com.example.androidapptemplate.features.webapi

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidapptemplate.R
import com.example.androidapptemplate.databinding.WebApiTopFragmentBinding
import com.example.androidapptemplate.features.common.SimpleListAdapter
import com.example.androidapptemplate.util.ToastHelper
import com.example.androidapptemplate.util.viewBindings
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class WebApiTopFragment : Fragment(R.layout.web_api_top_fragment) {
    private val viewModel by viewModels<WebApiTopViewModel>()
    private val binding by viewBindings(WebApiTopFragmentBinding::bind)

    private val simpleListAdapter =
        SimpleListAdapter(dataset = listOf("Trivia", "OpenWeatherMap", "CCC"), onItemClicked = {
            toastHelper.showToast(it)
        })

    @Inject
    lateinit var toastHelper: ToastHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.apply {
            adapter = simpleListAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}