package com.example.androidapptemplate.data.datasource.db

import android.content.Context
import androidx.room.Room
import com.example.androidapptemplate.data.datasource.db.dao.TriviaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    internal fun provideTriviaDatabase(@ApplicationContext context: Context): TriviaDatabase =
        Room.databaseBuilder(context, TriviaDatabase::class.java, "trivia.db").build()

    @Provides
    @Singleton
    internal fun provideTriviaDao(database: TriviaDatabase): TriviaDao =
        database.triviaDao()
}