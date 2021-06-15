package com.example.androidapptemplate.data.features.trivia.repository

import com.example.androidapptemplate.data.features.trivia.local.TriviaLocalDatasource
import com.example.androidapptemplate.data.features.trivia.remote.TriviaRemoteDatasource
import com.example.androidapptemplate.domain.features.trivia.repository.TriviaRepository
import javax.inject.Inject

internal class TriviaRepositoryImpl @Inject constructor(
    private val remoteDatasource: TriviaRemoteDatasource,
    private val localDatasource: TriviaLocalDatasource
) : TriviaRepository {
    override suspend fun getRandomTrivia(): String = remoteDatasource.getRandomTrivia()

    override suspend fun insertTriviaHistory(text: String) {
        localDatasource.insertTriviaHistory(text)
    }
}