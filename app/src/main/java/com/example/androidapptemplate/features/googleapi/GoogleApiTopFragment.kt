package com.example.androidapptemplate.features.googleapi

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidapptemplate.R
import com.example.androidapptemplate.databinding.GoogleApiTopFragmentBinding
import com.example.androidapptemplate.features.common.SimpleListAdapter
import com.example.androidapptemplate.util.ToastHelper
import com.example.androidapptemplate.util.viewBindings
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class GoogleApiTopFragment : Fragment(R.layout.google_api_top_fragment) {
    private val viewModel by viewModels<GoogleApiTopViewModel>()
    private val binding by viewBindings(GoogleApiTopFragmentBinding::bind)

    private val simpleListAdapter =
        SimpleListAdapter(dataset = listOf("Directions", "Place", "Map"), onItemClicked = {
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