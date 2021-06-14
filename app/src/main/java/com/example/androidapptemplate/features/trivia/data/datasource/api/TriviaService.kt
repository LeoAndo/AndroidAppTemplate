package com.example.androidapptemplate.features.trivia.data.datasource.api

import retrofit2.http.GET

internal interface TriviaService {
    @GET("random/trivia")
    suspend fun getRandomTrivia(): String
}