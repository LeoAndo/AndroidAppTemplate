package com.example.androidapptemplate.data.features.trivia.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidapptemplate.data.features.trivia.db.entity.TriviaEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface TriviaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTriviaHistory(trivia: TriviaEntity)

    // Trivia情報を全件取得する.
    @Query("SELECT * FROM trivia_histories")
    fun findAllTriviaList(): Flow<List<TriviaEntity>>
}