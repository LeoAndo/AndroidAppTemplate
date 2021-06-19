package com.example.androidapptemplate.features.webapi.trivia.history

import androidx.lifecycle.*
import com.example.androidapptemplate.domain.features.trivia.model.TriviaResult
import com.example.androidapptemplate.domain.features.trivia.usecase.TriviaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
internal class TriviaHistoryViewModel @Inject constructor(
    private val usecase: TriviaUseCase
) : ViewModel() {
    private val _failure = MutableLiveData<String>()
    val failure: LiveData<String> = _failure

    @OptIn(InternalCoroutinesApi::class)
    val resultTrivia: LiveData<List<TriviaResult>> =
        liveData(context = viewModelScope.coroutineContext) {
            runCatching {
                usecase.getAllTriviaList().collect { emit(it) }
            }.onFailure {
                _failure.value = it.localizedMessage
            }
        }
}