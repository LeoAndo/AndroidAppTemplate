package com.example.androidapptemplate.domain.repository

interface TriviaRepository {
    suspend fun getRandomTrivia(): String
    suspend fun insertTriviaHistory(text: String)
}