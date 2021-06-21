package com.example.androidapptemplate.features.webapi.trivia

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.androidapptemplate.R
import com.example.androidapptemplate.databinding.FragmentTriviaTopBinding
import com.example.androidapptemplate.core.util.viewBindings
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class TriviaTopFragment : Fragment(R.layout.fragment_trivia_top) {
    private val binding by viewBindings(FragmentTriviaTopBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // タブレイアウトの設定
        val pagerAdapter = TriviaPagerAdapter(this, requireContext())
        binding.viewPager.adapter = pagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = pagerAdapter.getPageTitle(position)
        }.attach() // viewpager2の場合のtabLayoutとの紐付け処理
        binding.tabLayout.tabMode = TabLayout.MODE_FIXED
        binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL
    }
}