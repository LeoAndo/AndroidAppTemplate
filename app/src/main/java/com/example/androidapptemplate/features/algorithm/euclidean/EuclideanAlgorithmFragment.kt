package com.example.androidapptemplate.features.algorithm.euclidean

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidapptemplate.R
import com.example.androidapptemplate.databinding.FragmentEuclideanAlgorithmBinding
import com.example.androidapptemplate.domain.features.algorithm.model.AlgorithmDetailsMessage
import com.example.androidapptemplate.core.util.ToastHelper
import com.example.androidapptemplate.core.util.viewBindings
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class EuclideanAlgorithmFragment : Fragment(R.layout.fragment_euclidean_algorithm) {
    private val binding by viewBindings(FragmentEuclideanAlgorithmBinding::bind)

    @Inject
    lateinit var toastHelper: ToastHelper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            try {
                val naturalNumberA = Integer.parseInt(binding.editTextNumberSigned.text.toString())
                val naturalNumberB = Integer.parseInt(binding.editTextNumberSigned2.text.toString())
                val result = gcd(naturalNumberA, naturalNumberB)
                binding.textResult.text = result.toString()
            } catch (ex: NumberFormatException) {
                toastHelper.showToast(ex.localizedMessage ?: "error")
            }
        }
        binding.button2.setOnClickListener {
            findNavController().navigate(
                EuclideanAlgorithmFragmentDirections.goToEuclideanAlgorithmDialogDest(
                    AlgorithmDetailsMessage(
                        overview = "overview",
                        link = "https://en.wikipedia.org/wiki/Euclidean_algorithm"
                    )
                )
            )
        }
    }

    // https://en.wikipedia.org/wiki/Euclidean_algorithm
    // ユークリッドの互除法を使った最大公約数の計算.
    private tailrec fun gcd(a: Int, b: Int): Int {
        if (b == 0) return a
        return gcd(b, a % b)
    }
}