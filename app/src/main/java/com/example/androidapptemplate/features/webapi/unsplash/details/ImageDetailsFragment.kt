package com.example.androidapptemplate.features.webapi.unsplash.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.androidapptemplate.R
import com.example.androidapptemplate.core.util.viewBindings
import com.example.androidapptemplate.databinding.FragmentImageDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class ImageDetailsFragment : Fragment(R.layout.fragment_image_details) {
    private val args by navArgs<ImageDetailsFragmentArgs>()
    private val binding by viewBindings(FragmentImageDetailsBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            val photo = args.photo
            this.photo = photo
            Glide.with(this@ImageDetailsFragment)
                .load(photo.urls.full)
                .error(R.drawable.ic_error)
                .into(imageView)
        }
    }
}