package com.example.androidapptemplate.data.datasource.local

import com.example.androidapptemplate.data.datasource.db.dao.TriviaDao
import com.example.androidapptemplate.data.datasource.db.entity.TriviaEntity
import com.example.androidapptemplate.data.dbCall
import javax.inject.Inject

internal class TriviaLocalDatasource @Inject constructor(
    private val dao: TriviaDao
) {
    suspend fun insertTriviaHistory(text: String) {
        val entity = TriviaEntity(0, text)
        dbCall { dao.insertTriviaHistory(entity) }
    }
}