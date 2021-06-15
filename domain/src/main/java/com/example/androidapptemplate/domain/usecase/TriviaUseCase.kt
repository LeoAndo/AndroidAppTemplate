package com.example.androidapptemplate.domain.usecase

import com.example.androidapptemplate.domain.repository.TriviaRepository
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