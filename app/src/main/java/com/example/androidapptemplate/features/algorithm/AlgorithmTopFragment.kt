package com.example.androidapptemplate.features.algorithm

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidapptemplate.R
import com.example.androidapptemplate.core.widget.SimpleListAdapter
import com.example.androidapptemplate.databinding.AlgorithmTopFragmentBinding
import com.example.androidapptemplate.core.util.ToastHelper
import com.example.androidapptemplate.core.util.viewBindings
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class AlgorithmTopFragment : Fragment(R.layout.algorithm_top_fragment) {
    private val binding by viewBindings(AlgorithmTopFragmentBinding::bind)

    private val simpleListAdapter = SimpleListAdapter(
        dataset = listOf("Euclidean Algorithm"),
        onItemClicked = {
            when (it) {
                "Euclidean Algorithm" -> {
                    findNavController().navigate(AlgorithmTopFragmentDirections.goToAlgorithmNavigation())
                }
                else -> {
                    toastHelper.showToast("does not implementation.. coming implementation soon.")
                }
            }
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