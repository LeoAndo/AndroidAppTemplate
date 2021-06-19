package com.example.androidapptemplate.features.login

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androidapptemplate.R
import com.example.androidapptemplate.domain.exception.InvalidEmailAddressException
import com.example.androidapptemplate.domain.exception.InvalidPasswordException
import com.example.androidapptemplate.core.dialog.NetworkConnectionErrorHandleable
import com.example.androidapptemplate.core.dialog.RetryConnectionHandler
import com.example.androidapptemplate.core.dialog.UnAuthorizedErrorHandleable
import kotlinx.coroutines.CoroutineExceptionHandler

internal class LoginExceptionHandler(
    private val fragment: Fragment,
    override val onUnAuthorizedAction: () -> Unit
) : RetryConnectionHandler(), UnAuthorizedErrorHandleable, NetworkConnectionErrorHandleable {
    internal val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        val context = fragment.requireContext()
        if (super.handleUnAuthorizedError(fragment, throwable)) return@CoroutineExceptionHandler
        if (super.handleNetworkConnectionError(
                listener,
                fragment,
                throwable
            )
        ) return@CoroutineExceptionHandler
        when (throwable) {
            is InvalidEmailAddressException -> {
                Toast.makeText(
                    context,
                    context.getString(R.string.invalid_email_address_message),
                    Toast.LENGTH_SHORT
                ).show()
            }
            is InvalidPasswordException -> {
                Toast.makeText(
                    context,
                    context.getString(R.string.invalid_password_message),
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
                Toast.makeText(
                    fragment.requireContext(),
                    throwable.localizedMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}