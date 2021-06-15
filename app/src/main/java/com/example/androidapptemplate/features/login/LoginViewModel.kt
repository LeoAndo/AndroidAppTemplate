package com.example.androidapptemplate.features.login

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapptemplate.domain.exception.InvalidEmailAddressException
import com.example.androidapptemplate.domain.exception.InvalidPasswordException
import com.example.androidapptemplate.domain.features.login.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    val emailAddress = ObservableField<String>()
    val password = ObservableField<String>()
    val isEnableLoginButton = ObservableBoolean()
    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess
    private val _invalidError = MutableLiveData<String>()
    val invalidError: LiveData<String> = _invalidError

    internal fun inputTextChanged() {
        isEnableLoginButton.set(
            !(emailAddress.get().isNullOrBlank() || password.get().isNullOrBlank())
        )
    }

    internal fun login() {
        viewModelScope.launch(coroutineExceptionHandler) {
            _loginSuccess.value =
                loginUseCase.login(emailAddress.get().orEmpty(), password.get().orEmpty())
        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("ExceptionHandler", "throwable: $throwable")
        when (throwable) {
            is InvalidEmailAddressException, is InvalidPasswordException -> {
                _invalidError.value = throwable.message
            }
            else -> {
                Log.e("ExceptionHandler", throwable.localizedMessage ?: "error")
            }
        }
    }
}