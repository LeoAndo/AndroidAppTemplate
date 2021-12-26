package com.example.androidapptemplate.features.login

internal sealed interface UiState {
    object Loading : UiState
    data class Success(val data: Boolean) : UiState
    data class Error(val throwable: Throwable) : UiState
}