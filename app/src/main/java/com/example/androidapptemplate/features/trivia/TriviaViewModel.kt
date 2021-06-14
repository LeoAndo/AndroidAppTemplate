package com.example.androidapptemplate.features.trivia

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapptemplate.domain.usecase.TriviaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TriviaViewModel @Inject constructor(
    private val usecase: TriviaUseCase,
    private val coroutineExceptionHandler: CoroutineExceptionHandler
) : ViewModel() {
    fun getRandomTrivia() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val ret = usecase.getRandomTrivia()
            Log.d("TriviaViewModel", "ret: $ret")
        }
    }
}