package com.example.androidapptemplate.features.trivia

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androidapptemplate.core.dialog.NetworkConnectionErrorHandleable
import com.example.androidapptemplate.core.dialog.RetryConnectionHandler
import com.example.androidapptemplate.core.dialog.UnAuthorizedErrorHandleable
import kotlinx.coroutines.CoroutineExceptionHandler

internal class TriviaExceptionHandler(
    private val fragment: Fragment,
    override val onUnAuthorizedAction: () -> Unit
) : RetryConnectionHandler(), UnAuthorizedErrorHandleable, NetworkConnectionErrorHandleable {
    internal val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        if (super.handleUnAuthorizedError(fragment, throwable)) return@CoroutineExceptionHandler
        if (super.handleNetworkConnectionError(
                listener,
                fragment,
                throwable
            )
        ) return@CoroutineExceptionHandler

        // default
        Toast.makeText(
            fragment.requireContext(),
            throwable.localizedMessage,
            Toast.LENGTH_SHORT
        ).show()
    }
}