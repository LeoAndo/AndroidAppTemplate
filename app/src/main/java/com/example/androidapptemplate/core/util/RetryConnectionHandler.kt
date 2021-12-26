package com.example.androidapptemplate.core.util

@Deprecated(message = "")
internal abstract class RetryConnectionHandler {
    internal var listener: OnRetryConnectionListener? = null
        private set

    @Deprecated(message = "")
    fun setOnRetryConnectionListener(listener: OnRetryConnectionListener) {
        this.listener = listener
    }
}