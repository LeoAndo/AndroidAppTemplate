package com.example.androidapptemplate.data.features.trivia.datasource

import com.example.androidapptemplate.data.core.dbCall
import com.example.androidapptemplate.data.features.trivia.db.dao.TriviaDao
import com.example.androidapptemplate.data.features.trivia.db.entity.TriviaEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class TriviaLocalDatasource @Inject constructor(
    private val dao: TriviaDao
) {
    suspend fun insertTriviaHistory(text: String) {
        val entity = TriviaEntity(0, text)
        dbCall { dao.insertTriviaHistory(entity) }
    }

    fun getAllTriviaList(): Flow<List<TriviaEntity>> = dao.findAllTriviaList()
}