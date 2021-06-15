package com.example.androidapptemplate.domain.features.trivia.usecase

import com.example.androidapptemplate.domain.features.trivia.repository.TriviaRepository
import javax.inject.Inject

interface TriviaUseCase {
    suspend fun getRandomTrivia(): String
}

class TriviaUseCaseImpl @Inject constructor(
    private val triviaRepository: TriviaRepository
) : TriviaUseCase {
    override suspend fun getRandomTrivia(): String {
        val result = triviaRepository.getRandomTrivia()
        triviaRepository.insertTriviaHistory(result)
        return result
    }
}