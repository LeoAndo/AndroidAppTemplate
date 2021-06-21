package com.example.androidapptemplate.data.features.trivia.api

import retrofit2.http.GET
import retrofit2.http.Path

internal interface TriviaService {
    @GET("{month}/{date}")
    suspend fun getTrivia(
        @Path("month") month: String, @Path("date") date: String
    ): String

    @GET("random/trivia")
    suspend fun getRandomTrivia(): String

    @GET("random/year")
    suspend fun getRandomYear(): String

    @GET("random/math")
    suspend fun getRandomMath(): String

    @GET("random/date")
    suspend fun getRandomDate(): String
}