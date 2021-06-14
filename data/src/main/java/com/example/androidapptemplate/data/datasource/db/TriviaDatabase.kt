package com.example.androidapptemplate.data.datasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidapptemplate.data.datasource.db.dao.TriviaDao
import com.example.androidapptemplate.data.datasource.db.entity.TriviaEntity

@Database(entities = [TriviaEntity::class], version = 1, exportSchema = false)
internal abstract class TriviaDatabase : RoomDatabase() {
    abstract fun triviaDao(): TriviaDao
}