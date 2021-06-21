package com.example.androidapptemplate.features.webapi.unsplash.gallery

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androidapptemplate.core.util.NetworkConnectionErrorHandleable
import com.example.androidapptemplate.core.util.RetryConnectionHandler
import com.example.androidapptemplate.core.util.UnSplashUnAuthorizedErrorHandleable

internal class UnsplashGalleryExceptionHandler(
    private val fragment: Fragment,
    override val onUnAuthorizedAction: () -> Unit
) : RetryConnectionHandler(), UnSplashUnAuthorizedErrorHandleable,
    NetworkConnectionErrorHandleable {
    internal fun handleError(throwable: Throwable) {
        if (super.handleUnAuthorizedError(fragment, throwable)) return
        if (super.handleNetworkConnectionError(listener, fragment, throwable)) return

        // default
        Toast.makeText(
            fragment.requireContext(),
            throwable.localizedMessage,
            Toast.LENGTH_SHORT
        ).show()
    }
}