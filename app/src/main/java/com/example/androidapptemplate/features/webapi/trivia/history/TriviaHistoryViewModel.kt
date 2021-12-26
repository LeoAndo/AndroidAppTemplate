package com.example.androidapptemplate.features.webapi.trivia.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapptemplate.domain.features.webapi.trivia.usecase.TriviaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class TriviaHistoryViewModel @Inject constructor(
    private val usecase: TriviaUseCase
) : ViewModel() {
    private val _uistate = MutableLiveData<UiState>()
    val uistate: LiveData<UiState> = _uistate

    init {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            _uistate.value = UiState.Error(throwable)
        }) {
            usecase.getAllTriviaList().collect {
                _uistate.value = UiState.Success(it)
            }
        }
    }
}