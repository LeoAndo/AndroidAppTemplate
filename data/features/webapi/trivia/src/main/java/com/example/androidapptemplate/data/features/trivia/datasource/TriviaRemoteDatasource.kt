package com.example.androidapptemplate.data.features.trivia.datasource

import com.example.androidapptemplate.data.core.apiCall
import com.example.androidapptemplate.data.features.trivia.api.TriviaService
import javax.inject.Inject

internal class TriviaRemoteDatasource @Inject constructor(
    private val triviaService: TriviaService
) {
    suspend fun getTrivia(month: String, date: String): String =
        triviaService.getTrivia(month, date)

    suspend fun getRandomTrivia(): String = apiCall { triviaService.getRandomTrivia() }
    suspend fun getRandomYear(): String = apiCall { triviaService.getRandomYear() }
    suspend fun getRandomMath(): String = apiCall { triviaService.getRandomMath() }
    suspend fun getRandomDate(): String = apiCall { triviaService.getRandomDate() }
}