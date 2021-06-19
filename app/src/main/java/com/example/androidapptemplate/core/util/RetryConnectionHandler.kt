package com.example.androidapptemplate.core.util

import com.example.androidapptemplate.core.dialog.OnRetryConnectionListener

internal abstract class RetryConnectionHandler {
    internal var listener: OnRetryConnectionListener? = null
        private set

    fun setOnRetryConnectionListener(listener: OnRetryConnectionListener) {
        this.listener = listener
    }
}