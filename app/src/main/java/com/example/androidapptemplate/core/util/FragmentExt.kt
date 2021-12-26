package com.example.androidapptemplate.core.util

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androidapptemplate.R
import com.example.androidapptemplate.data.core.ErrorResult
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Fragment.handleNetworkConnectionError(throwable: Throwable, onRetry: () -> Unit) {
    when (throwable) {
        is ErrorResult.BadRequestError, is ErrorResult.NotFoundError -> {
            Toast.makeText(requireContext(), throwable.message, Toast.LENGTH_SHORT).show()
        }
        is ErrorResult.NetworkError -> {
            openAlertDialog(
                onPositiveButtonClicked = { onRetry() },
                onNegativeButtonClicked = {},
                title = requireContext().getString(R.string.network_error_dialog_title),
                message = requireContext().getString(R.string.network_error_dialog_message)
            )
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
            Log.w("handleUnAuthorizedError", "error: ${throwable.localizedMessage}")
        }
    }
}

fun Fragment.handleUnSplashUnAuthorizedError(
    throwable: Throwable,
    onUnAuthorizedAction: () -> Unit
) {
    when (throwable) {
        is ErrorResult.UnAuthorizedError -> {
            openAlertDialog(
                onPositiveButtonClicked = { onUnAuthorizedAction() },
                onNegativeButtonClicked = {},
                title = requireContext().getString(R.string.un_splash_un_authorized_dialog_title),
                message = requireContext().getString(R.string.un_splash_un_authorized_dialog_message),
                isCancelable = true
            )
        }
        else -> {
            Log.w("handleUnAuthorizedError", "error: ${throwable.localizedMessage}")
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

internal fun Fragment.openAlertDialog(
    onPositiveButtonClicked: () -> Unit,
    onNegativeButtonClicked: () -> Unit,
    title: String = "",
    message: String = "",
    isCancelable: Boolean = false
) {
    MaterialAlertDialogBuilder(requireActivity()).apply {
        setTitle(title)
        setMessage(message)
        setPositiveButton(android.R.string.ok) { _, _ -> onPositiveButtonClicked() }
        setNegativeButton(android.R.string.cancel) { _, _ -> onNegativeButtonClicked() }
        setCancelable(isCancelable)
    }.create().apply {
        setCanceledOnTouchOutside(isCancelable)
        show()
    }
}