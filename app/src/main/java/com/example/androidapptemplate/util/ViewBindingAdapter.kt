package com.example.androidapptemplate.util

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visible_or_gone")
fun View.setVisibleOrGone(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}