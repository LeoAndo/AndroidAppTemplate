package com.example.androidapptemplate.data.features.trivia.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidapptemplate.data.features.trivia.db.dao.TriviaDao
import com.example.androidapptemplate.data.features.trivia.db.entity.TriviaEntity

@Database(entities = [TriviaEntity::class], version = 1, exportSchema = false)
internal abstract class TriviaDatabase : RoomDatabase() {
    abstract fun triviaDao(): TriviaDao

    companion object {
        @Volatile
        private var INSTANCE: TriviaDatabase? = null
        fun getInstance(context: Context): TriviaDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, TriviaDatabase::class.java, "trivia.db").build()
                    .also {
                        INSTANCE = it
                    }
            }
        }
    }
}