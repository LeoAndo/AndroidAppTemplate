package com.example.androidapptemplate.features.trivia.data.datasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidapptemplate.features.trivia.data.datasource.db.dao.TriviaDao
import com.example.androidapptemplate.features.trivia.data.datasource.db.entity.TriviaEntity

@Database(entities = [TriviaEntity::class], version = 1, exportSchema = false)
internal abstract class TriviaDatabase : RoomDatabase() {
    abstract fun triviaDao(): TriviaDao
}