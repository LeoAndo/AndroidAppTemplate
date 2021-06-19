package com.example.androidapptemplate.domain.features.webapi.trivia.usecase

import com.example.androidapptemplate.domain.features.webapi.trivia.model.TriviaResult
import com.example.androidapptemplate.domain.features.webapi.trivia.repository.TriviaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface TriviaUseCase {
    suspend fun getTrivia(month: String, date: String): String
    suspend fun getRandomTrivia(): String
    suspend fun getRandomYear(): String
    suspend fun getRandomMath(): String
    suspend fun getRandomDate(): String
    fun getAllTriviaList(): Flow<List<TriviaResult>>
}

class TriviaUseCaseImpl @Inject constructor(
    private val triviaRepository: TriviaRepository
) : TriviaUseCase {
    override suspend fun getTrivia(month: String, date: String): String {
        val apiResult = triviaRepository.getTrivia(month, date)
        triviaRepository.insertTriviaHistory(apiResult)
        return apiResult
    }

    override suspend fun getRandomTrivia(): String {
        val result = triviaRepository.getRandomTrivia()
        triviaRepository.insertTriviaHistory(result)
        return result
    }

    override suspend fun getRandomYear(): String {
        val apiResult = triviaRepository.getRandomYear()
        triviaRepository.insertTriviaHistory(apiResult)
        return apiResult
    }

    override suspend fun getRandomMath(): String {
        val apiResult = triviaRepository.getRandomMath()
        triviaRepository.insertTriviaHistory(apiResult)
        return apiResult
    }

    override suspend fun getRandomDate(): String {
        val apiResult = triviaRepository.getRandomDate()
        triviaRepository.insertTriviaHistory(apiResult)
        return apiResult
    }

    override fun getAllTriviaList(): Flow<List<TriviaResult>> {
        return triviaRepository.getAllTriviaList()
    }
}