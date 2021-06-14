package com.example.androidapptemplate.features.trivia.data.datasource.remote

import com.example.androidapptemplate.features.trivia.data.apiCall
import com.example.androidapptemplate.features.trivia.data.datasource.api.TriviaService
import javax.inject.Inject

internal class TriviaRemoteDatasource @Inject constructor(
    private val triviaService: TriviaService
) {
    suspend fun getRandomTrivia(): String {
        return apiCall { triviaService.getRandomTrivia() }
    }
}