package com.example.androidapptemplate.di

import android.accounts.NetworkErrorException
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.androidapptemplate.domain.exception.BadRequestErrorException
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineExceptionHandler

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideCoroutineExceptionHandler(@ApplicationContext app: Context): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, throwable ->
            Log.e("ExceptionHandler", "hashcode: ${hashCode()}")
            when (throwable) {
                is NetworkErrorException -> {
                    Toast.makeText(app, "NetworkErrorException時の処理を行う", Toast.LENGTH_SHORT).show()
                }
                is BadRequestErrorException -> {
                    Toast.makeText(app, "BadRequestErrorException時の処理を行う", Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    Toast.makeText(app, throwable.localizedMessage ?: "error", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}