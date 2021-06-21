package com.example.androidapptemplate.data.features.auth.repository

import com.example.androidapptemplate.data.features.auth.datasource.AuthRemoteDataSource
import com.example.androidapptemplate.domain.features.auth.repository.AuthRepository
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val loginRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {
    override suspend fun login(emailAddress: String, password: String): Boolean {
        loginRemoteDataSource.login(emailAddress, password)
        return true
    }
}