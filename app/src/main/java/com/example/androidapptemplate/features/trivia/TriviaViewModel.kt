package com.example.androidapptemplate.features.trivia

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapptemplate.domain.exception.BadRequestErrorException
import com.example.androidapptemplate.domain.usecase.TriviaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TriviaViewModel @Inject constructor(
    private val usecase: TriviaUseCase
) : ViewModel() {
    fun getRandomTrivia() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val ret = usecase.getRandomTrivia()
            Log.d("TriviaViewModel", "ret: $ret")
        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("ExceptionHandler", "throwable: $throwable")
        when (throwable) {
            is NetworkErrorException -> {
                Log.e("ExceptionHandler", "NetworkErrorException時の処理を行う")
            }
            is BadRequestErrorException -> {
                Log.e("ExceptionHandler", "BadRequestErrorException時の処理を行う")
            }
            else -> {
                Log.e("ExceptionHandler", "throwable.localizedMessage ?: \"error\"")
            }
        }
    }
}