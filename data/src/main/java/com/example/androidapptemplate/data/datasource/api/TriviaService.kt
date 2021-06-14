package com.example.androidapptemplate.data.datasource.api

import retrofit2.http.GET

internal interface TriviaService {
    @GET("random/trivia")
    suspend fun getRandomTrivia(): String
}