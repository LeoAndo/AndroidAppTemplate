package com.example.androidapptemplate.data.datasource.repository

import com.example.androidapptemplate.data.datasource.local.TriviaLocalDatasource
import com.example.androidapptemplate.data.datasource.remote.TriviaRemoteDatasource
import com.example.androidapptemplate.domain.repository.TriviaRepository
import javax.inject.Inject

internal class TriviaRepositoryImpl @Inject constructor(
    private val remoteDatasource: TriviaRemoteDatasource,
    private val localDatasource: TriviaLocalDatasource
) : TriviaRepository {
    override suspend fun getRandomTrivia(): String = remoteDatasource.getRandomTrivia()

    override suspend fun insertTriviaInfo(text: String) {
        localDatasource.insertTriviaInfo(text)
    }
}