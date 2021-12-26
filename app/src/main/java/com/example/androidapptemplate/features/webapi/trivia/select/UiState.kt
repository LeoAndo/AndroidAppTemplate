package com.example.androidapptemplate.features.webapi.trivia.select

internal sealed interface UiState {
    object Loading : UiState
    data class Success(val data: String) : UiState
    data class Error(val throwable: Throwable) : UiState
}