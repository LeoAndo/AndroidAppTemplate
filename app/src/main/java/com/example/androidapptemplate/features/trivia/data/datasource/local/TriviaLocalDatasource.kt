package com.example.androidapptemplate.features.trivia.data.datasource.local

import com.example.androidapptemplate.features.trivia.data.datasource.db.dao.TriviaDao
import com.example.androidapptemplate.features.trivia.data.datasource.db.entity.TriviaEntity
import com.example.androidapptemplate.features.trivia.data.dbCall
import javax.inject.Inject

internal class TriviaLocalDatasource @Inject constructor(
    private val dao: TriviaDao
) {
    suspend fun insertTriviaInfo(text: String) {
        val entity = TriviaEntity(0, text)
        dbCall { dao.insertTriviaTable(entity) }
    }
}