package com.example.androidapptemplate.features.webapi.trivia.history

import com.example.androidapptemplate.domain.features.webapi.trivia.model.TriviaResult

internal sealed interface UiState {
    data class Success(val data: List<TriviaResult>) : UiState
    data class Error(val throwable: Throwable) : UiState
}