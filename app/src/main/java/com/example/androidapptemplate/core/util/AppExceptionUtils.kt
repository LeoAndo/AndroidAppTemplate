package com.example.androidapptemplate.core.util

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androidapptemplate.R
import com.example.androidapptemplate.data.core.ErrorResult
import com.example.androidapptemplate.domain.exception.BadRequestException
import com.example.androidapptemplate.domain.exception.NetworkException
import com.example.androidapptemplate.domain.exception.NotFoundException
import com.example.androidapptemplate.domain.exception.UnAuthorizedException
import com.google.android.material.dialog.MaterialAlertDialogBuilder

@Deprecated("messae")
internal interface OnRetryConnectionListener {
    fun onRetry()
}

internal interface NetworkConnectionErrorHandleable {
    fun handleNetworkConnectionError(
        listener: OnRetryConnectionListener?,
        fragment: Fragment,
        throwable: Throwable
    ): Boolean {
        return when (throwable) {
            is BadRequestException, is NotFoundException -> {
                Toast.makeText(fragment.requireContext(), throwable.message, Toast.LENGTH_SHORT)
                    .show()
                true
            }
            is NetworkException -> {
                fragment.openNetworkErrorDialog { listener?.onRetry() }
                true
            }
            else -> false
        }
    }
}

fun Fragment.handleNetworkConnectionError(throwable: Throwable, onRetry: () -> Unit) {
    when (throwable) {
        is ErrorResult.BadRequestError, is ErrorResult.NotFoundError -> {
            Toast.makeText(requireContext(), throwable.message, Toast.LENGTH_SHORT).show()
        }
        is ErrorResult.NetworkError -> {
            openNetworkErrorDialog { onRetry() }
        }
        else -> {
            Log.w("handleNetworkConnectionError", "error: ${throwable.localizedMessage}")
        }
    }
}

fun Fragment.handleUnAuthorizedError(throwable: Throwable, onUnAuthorizedAction: () -> Unit) {
    when (throwable) {
        is ErrorResult.UnAuthorizedError -> {
            openUnAuthorizedErrorDialog { onUnAuthorizedAction() }
        }
        else -> {
            Log.w("handleNetworkConnectionError", "error: ${throwable.localizedMessage}")
        }
    }
}

internal interface UnSplashUnAuthorizedErrorHandleable {
    val onUnAuthorizedAction: () -> Unit
    fun handleUnAuthorizedError(fragment: Fragment, throwable: Throwable): Boolean {
        return when (throwable) {
            is UnAuthorizedException -> {
                fragment.openUnSplashUnAuthorizedErrorDialog { onUnAuthorizedAction() }
                true
            }
            else -> false
        }
    }
}

internal fun Fragment.openUnAuthorizedErrorDialog(onPositiveButtonClicked: () -> Unit) {
    MaterialAlertDialogBuilder(requireActivity()).apply {
        setTitle(requireContext().getString(R.string.un_authorized_dialog_title))
        setMessage(requireContext().getString(R.string.un_authorized_dialog_message))
        setPositiveButton(android.R.string.ok) { _, _ ->
            onPositiveButtonClicked()
        }
        setCancelable(false)
    }.create().apply {
        setCanceledOnTouchOutside(false)
        show()
    }
}

internal fun Fragment.openNetworkErrorDialog(onPositiveButtonClicked: () -> Unit) {
    MaterialAlertDialogBuilder(requireActivity()).apply {
        setTitle(requireContext().getString(R.string.network_error_dialog_title))
        setMessage(requireContext().getString(R.string.network_error_dialog_message))
        setPositiveButton(android.R.string.ok) { _, _ -> onPositiveButtonClicked() }
        setNegativeButton(android.R.string.cancel) { _, _ -> }
    }.create().apply {
        show()
    }
}

internal fun Fragment.openUnSplashUnAuthorizedErrorDialog(onPositiveButtonClicked: () -> Unit) {
    MaterialAlertDialogBuilder(requireActivity()).apply {
        setTitle(requireContext().getString(R.string.un_splash_un_authorized_dialog_title))
        setMessage(requireContext().getString(R.string.un_splash_un_authorized_dialog_message))
        setPositiveButton(android.R.string.ok) { _, _ ->
            onPositiveButtonClicked()
        }
        setCancelable(false)
    }.create().apply {
        setCanceledOnTouchOutside(false)
        show()
    }
}