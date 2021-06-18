package com.example.androidapptemplate.features.trivia

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.androidapptemplate.domain.features.trivia.usecase.TriviaUseCase
import com.example.androidapptemplate.features.core.dialog.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class TriviaRandomViewModel @Inject constructor(
    private val usecase: TriviaUseCase
) : ViewModel() {
    val resultTrivia = ObservableField<String>()
    var retryState: RetryState = RetryStateNone
        private set

    internal suspend fun getRandomMonthTrivia() {
        retryState = RetryState1
        resultTrivia.set(usecase.getRandomMath())
    }

    internal suspend fun getRandomTrivia() {
        retryState = RetryState2
        resultTrivia.set(usecase.getRandomTrivia())
    }

    internal suspend fun getRandomYearTrivia() {
        retryState = RetryState3
        resultTrivia.set(usecase.getRandomYear())
    }

    internal suspend fun getRandomDateTrivia() {
        retryState = RetryState4
        resultTrivia.set(usecase.getRandomDate())
    }
}