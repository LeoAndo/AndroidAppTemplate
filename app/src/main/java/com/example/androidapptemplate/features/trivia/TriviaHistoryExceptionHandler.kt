package com.example.androidapptemplate.features.trivia

import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineExceptionHandler

internal class TriviaHistoryExceptionHandler(private val fragment: Fragment) {
    internal val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        // default
        Toast.makeText(
            fragment.requireContext(),
            throwable.localizedMessage,
            Toast.LENGTH_SHORT
        ).show()
    }
}