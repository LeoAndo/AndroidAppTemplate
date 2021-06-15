package com.example.androidapptemplate.domain.features.auth.repository

interface AuthRepository {
    suspend fun login(emailAddress: String, password: String): Boolean
}