package com.example.androidapptemplate.domain.features.login.usecase

import com.example.androidapptemplate.domain.exception.InvalidEmailAddressException
import com.example.androidapptemplate.domain.exception.InvalidPasswordException
import com.example.androidapptemplate.domain.features.auth.repository.AuthRepository
import java.util.regex.Pattern
import javax.inject.Inject

interface LoginUseCase {
    suspend fun login(emailAddress: String, password: String): Boolean
}

class LoginUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
) : LoginUseCase {

    override suspend fun login(emailAddress: String, password: String): Boolean {
        if (!isEmailAddressValid(emailAddress)) {
            throw InvalidEmailAddressException("")
        } else if (!isPasswordValid(password)) {
            throw InvalidPasswordException("")
        }
        return repository.login(emailAddress, password)
    }

    private fun isEmailAddressValid(emailAddress: String): Boolean {
        return EMAIL_ADDRESS.matcher(emailAddress).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 4
    }

    companion object {
        // see: android/util/Patterns.java
        private val EMAIL_ADDRESS = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )
    }
}