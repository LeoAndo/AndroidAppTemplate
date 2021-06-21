package com.example.androidapptemplate.features.home

import androidx.fragment.app.Fragment
import com.example.androidapptemplate.R
import com.example.androidapptemplate.databinding.FragmentHomeBinding
import com.example.androidapptemplate.core.util.viewBindings
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBindings(FragmentHomeBinding::bind)
}