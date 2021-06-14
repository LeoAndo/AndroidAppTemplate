package com.example.androidapptemplate.data.datasource.remote

import com.example.androidapptemplate.data.apiCall
import com.example.androidapptemplate.data.datasource.api.TriviaService
import javax.inject.Inject

internal class TriviaRemoteDatasource @Inject constructor(
    private val triviaService: TriviaService
) {
    suspend fun getRandomTrivia(): String {
        return apiCall { triviaService.getRandomTrivia() }
    }
}