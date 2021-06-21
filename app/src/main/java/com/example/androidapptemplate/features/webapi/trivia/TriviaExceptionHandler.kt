package com.example.androidapptemplate.features.webapi.trivia

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androidapptemplate.core.util.NetworkConnectionErrorHandleable
import com.example.androidapptemplate.core.util.RetryConnectionHandler
import kotlinx.coroutines.CoroutineExceptionHandler

internal class TriviaExceptionHandler(private val fragment: Fragment) : RetryConnectionHandler(),
    NetworkConnectionErrorHandleable {
    internal val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
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