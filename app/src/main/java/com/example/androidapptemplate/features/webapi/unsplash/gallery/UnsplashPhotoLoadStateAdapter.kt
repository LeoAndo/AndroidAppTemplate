package com.example.androidapptemplate.features.webapi.unsplash.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidapptemplate.databinding.NetworkStateItemBinding

internal class UnsplashPhotoLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<UnsplashPhotoLoadStateAdapter.LoadStateAdapterViewHolder>() {

    inner class LoadStateAdapterViewHolder(private val binding: NetworkStateItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTo(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                retryButton.isVisible = loadState is LoadState.Error
                errorMsg.isVisible =
                    !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
                errorMsg.text = (loadState as? LoadState.Error)?.error?.message
                retryButton.setOnClickListener { retry.invoke() }
            }
        }
    }

    override fun onBindViewHolder(holder: LoadStateAdapterViewHolder, loadState: LoadState) {
        holder.bindTo(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadStateAdapterViewHolder {
        val binding = NetworkStateItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LoadStateAdapterViewHolder(binding)
    }
}