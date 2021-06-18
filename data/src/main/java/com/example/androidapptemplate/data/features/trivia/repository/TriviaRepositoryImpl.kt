package com.example.androidapptemplate.data.features.trivia.repository

import com.example.androidapptemplate.data.features.trivia.db.entity.toModel
import com.example.androidapptemplate.data.features.trivia.local.TriviaLocalDatasource
import com.example.androidapptemplate.data.features.trivia.remote.TriviaRemoteDatasource
import com.example.androidapptemplate.domain.features.trivia.model.TriviaResult
import com.example.androidapptemplate.domain.features.trivia.repository.TriviaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class TriviaRepositoryImpl @Inject constructor(
    private val remoteDatasource: TriviaRemoteDatasource,
    private val localDatasource: TriviaLocalDatasource
) : TriviaRepository {
    override suspend fun insertTriviaHistory(text: String) =
        localDatasource.insertTriviaHistory(text)

    override fun getAllTriviaList(): Flow<List<TriviaResult>> {
        return localDatasource.getAllTriviaList().map { list ->
            list.map { it.toModel() }
        }
    }

    override suspend fun getTrivia(month: String, date: String): String =
        remoteDatasource.getTrivia(month, date)

    override suspend fun getRandomTrivia(): String = remoteDatasource.getRandomTrivia()

    override suspend fun getRandomYear(): String = remoteDatasource.getRandomYear()

    override suspend fun getRandomMath(): String = remoteDatasource.getRandomMath()

    override suspend fun getRandomDate(): String = remoteDatasource.getRandomDate()
}