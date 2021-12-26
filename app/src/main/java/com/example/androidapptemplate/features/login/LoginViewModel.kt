package com.example.androidapptemplate.features.login

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val _uistate = MutableLiveData<UiState>()
    val uistate: LiveData<UiState> = _uistate

    internal fun inputTextChanged() {
        isEnableLoginButton.set(
            !(emailAddress.get().isNullOrBlank() || password.get().isNullOrBlank())
        )
    }

    fun login() {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            _uistate.value = UiState.Error(throwable)
        }) {
            _uistate.value = UiState.Loading
            val loginResult =
                loginUseCase.login(emailAddress.get().orEmpty(), password.get().orEmpty())
            _uistate.value = UiState.Success(loginResult)
        }
    }
}