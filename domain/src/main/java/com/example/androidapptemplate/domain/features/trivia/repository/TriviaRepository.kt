package com.example.androidapptemplate.domain.features.trivia.repository

interface TriviaRepository {
    suspend fun getRandomTrivia(): String
    suspend fun insertTriviaHistory(text: String)
}