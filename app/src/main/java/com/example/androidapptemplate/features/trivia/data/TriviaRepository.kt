package com.example.androidapptemplate.features.trivia.data

import com.example.androidapptemplate.features.trivia.data.datasource.local.TriviaLocalDatasource
import com.example.androidapptemplate.features.trivia.data.datasource.remote.TriviaRemoteDatasource
import javax.inject.Inject

interface TriviaRepository {
    suspend fun getRandomTrivia(): String
    suspend fun insertTriviaInfo(text: String)
}

internal class TriviaRepositoryImpl @Inject constructor(
    private val remoteDatasource: TriviaRemoteDatasource,
    private val localDatasource: TriviaLocalDatasource
) : TriviaRepository {
    override suspend fun getRandomTrivia(): String = remoteDatasource.getRandomTrivia()

    override suspend fun insertTriviaInfo(text: String) {
        localDatasource.insertTriviaInfo(text)
    }
}