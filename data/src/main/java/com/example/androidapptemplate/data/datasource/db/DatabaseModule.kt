package com.example.androidapptemplate.data.datasource.db

import android.content.Context
import com.example.androidapptemplate.data.datasource.db.dao.TriviaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    internal fun provideTriviaDatabase(@ApplicationContext context: Context): TriviaDatabase =
        TriviaDatabase.getInstance(context)

    @Provides
    internal fun provideTriviaDao(database: TriviaDatabase): TriviaDao =
        database.triviaDao()
}