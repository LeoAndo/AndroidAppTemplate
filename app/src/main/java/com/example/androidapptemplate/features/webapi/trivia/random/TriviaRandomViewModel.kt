package com.example.androidapptemplate.features.webapi.trivia.random

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapptemplate.domain.features.webapi.trivia.usecase.TriviaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class TriviaRandomViewModel @Inject constructor(
    private val usecase: TriviaUseCase
) : ViewModel() {
    private val _uistate = MutableLiveData<UiState>()
    val uistate: LiveData<UiState> = _uistate

    fun getRandomMonthTrivia() {
        doAction { usecase.getRandomMath() }
    }

    fun getRandomTrivia() {
        doAction { usecase.getRandomTrivia() }
    }

    fun getRandomYearTrivia() {
        doAction { usecase.getRandomYear() }
    }

    fun getRandomDateTrivia() {
        doAction { usecase.getRandomDate() }
    }

    private fun doAction(action: suspend () -> String) {
        // 特定のスコープの全てのジョブを明示的にキャンセルする場合は、以下で良さそう.
        // viewModelScope.coroutineContext.cancelChildren()
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            _uistate.value = UiState.Error(throwable)
        }) {
            _uistate.value = UiState.Loading
            UiState.Success(action.invoke())
        }
    }
}