package com.example.androidapptemplate.data.features.trivia.di

import android.content.Context
import com.example.androidapptemplate.data.features.trivia.db.TriviaDatabase
import com.example.androidapptemplate.data.features.trivia.db.dao.TriviaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    internal fun provideTriviaDatabase(@ApplicationContext context: Context): TriviaDatabase =
        TriviaDatabase.getInstance(context)

    @Provides
    internal fun provideTriviaDao(database: TriviaDatabase): TriviaDao =
        database.triviaDao()
}