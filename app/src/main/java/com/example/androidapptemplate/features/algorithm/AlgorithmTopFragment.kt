package com.example.androidapptemplate.features.algorithm

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidapptemplate.R
import com.example.androidapptemplate.databinding.AlgorithmTopFragmentBinding
import com.example.androidapptemplate.features.common.SimpleListAdapter
import com.example.androidapptemplate.util.ToastHelper
import com.example.androidapptemplate.util.viewBindings
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class AlgorithmTopFragment : Fragment(R.layout.algorithm_top_fragment) {
    private val viewModel by viewModels<AlgorithmTopViewModel>()
    private val binding by viewBindings(AlgorithmTopFragmentBinding::bind)

    private val simpleListAdapter =
        SimpleListAdapter(dataset = listOf("ユークリッドの互除法", "再帰検索", "CCC"), onItemClicked = {
            toastHelper.showToast(it)
        })

    @Inject
    lateinit var toastHelper: ToastHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.apply {
            adapter = simpleListAdapter
            setHasFixedSize(true) // recyclerViewがmath_parentでサイズ不変ならtrue指定しておくとパフォーマンス良い
            layoutManager = LinearLayoutManager(requireContext())
            // 境界線の設定
//            addItemDecoration(
//                DividerItemDecoration(
//                    requireContext(),
//                    DividerItemDecoration.VERTICAL
//                )
//            )
        }
    }
}