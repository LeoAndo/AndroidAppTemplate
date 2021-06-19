package com.example.androidapptemplate.core.dialog

internal abstract class RetryConnectionHandler {
    internal var listener: OnRetryConnectionListener? = null
        private set

    fun setOnRetryConnectionListener(listener: OnRetryConnectionListener) {
        this.listener = listener
    }
}