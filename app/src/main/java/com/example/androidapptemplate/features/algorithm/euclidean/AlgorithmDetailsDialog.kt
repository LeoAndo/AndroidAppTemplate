package com.example.androidapptemplate.features.algorithm.euclidean

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.example.androidapptemplate.core.app.AppBottomSheetDialogFragment
import com.example.androidapptemplate.databinding.DialogAlgorithmDetailsBinding
import com.example.androidapptemplate.core.util.ToastHelper
import javax.inject.Inject

internal class AlgorithmDetailsDialog : AppBottomSheetDialogFragment() {
    private var binding: DialogAlgorithmDetailsBinding? = null
    private val safeArgs by navArgs<AlgorithmDetailsDialogArgs>()

    @Inject
    lateinit var toastHelper: ToastHelper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DialogAlgorithmDetailsBinding.inflate(inflater, container, false).apply {
            binding = this
            textOverview.text = safeArgs.message.overview
            button3.apply {
                isVisible = !safeArgs.message.link.isNullOrEmpty()
                setOnClickListener { startExternalApp() }
            }
        }.root
    }

    private fun startExternalApp() {
        kotlin.runCatching {
            startActivity(
                Intent(Intent.ACTION_VIEW).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .setData(Uri.parse(safeArgs.message.link))
            )
        }.onFailure {
            when (it) {
                is NullPointerException, is ActivityNotFoundException -> {
                    toastHelper.showToast(it.localizedMessage ?: "error")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}