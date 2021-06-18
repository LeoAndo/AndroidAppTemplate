package com.example.androidapptemplate.features.trivia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidapptemplate.domain.features.trivia.usecase.TriviaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class TriviaViewModel @Inject constructor(
    private val usecase: TriviaUseCase
) : ViewModel() {
    private val _result = MutableLiveData<String>()
    val result: LiveData<String> = _result
    suspend fun getRandomTrivia() {
        _result.value = usecase.getRandomTrivia()
    }
}