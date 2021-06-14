package com.example.androidapptemplate.features.trivia.domain

import com.example.androidapptemplate.features.trivia.data.TriviaRepository
import javax.inject.Inject

interface TriviaUseCase {
    suspend fun getRandomTrivia(): String
}

internal class TriviaUseCaseImpl @Inject constructor(
    private val triviaRepository: TriviaRepository
) : TriviaUseCase {
    override suspend fun getRandomTrivia(): String {
        val result = triviaRepository.getRandomTrivia()
        triviaRepository.insertTriviaInfo(result)
        return result
    }
}