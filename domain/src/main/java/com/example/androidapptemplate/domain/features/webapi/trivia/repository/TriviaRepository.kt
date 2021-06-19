package com.example.androidapptemplate.domain.features.webapi.trivia.repository

import com.example.androidapptemplate.domain.features.webapi.trivia.model.TriviaResult
import kotlinx.coroutines.flow.Flow

interface TriviaRepository {
    suspend fun insertTriviaHistory(text: String)
    fun getAllTriviaList(): Flow<List<TriviaResult>>
    suspend fun getTrivia(month: String, date: String): String
    suspend fun getRandomTrivia(): String
    suspend fun getRandomYear(): String
    suspend fun getRandomMath(): String
    suspend fun getRandomDate(): String
}