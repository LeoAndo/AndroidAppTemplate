package com.example.androidapptemplate.features.webapi.trivia.select

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidapptemplate.core.util.getDateValue
import com.example.androidapptemplate.core.util.getMonthValue
import com.example.androidapptemplate.domain.features.webapi.trivia.usecase.TriviaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

// layoutでObservableFieldとか参照しているので、ViewModel自体をinternalにできない.
@HiltViewModel
internal class TriviaSelectViewModel @Inject constructor(
    private val usecase: TriviaUseCase
) : ViewModel() {
    val selectedMonthValue = ObservableField<String>()
    val selectedDateValue = ObservableField<String>()
    val monthDropdownValues = ObservableField(getMonthValue())
    val dateDropdownValues = ObservableField(getDateValue())
    private val _uistate = MutableLiveData<UiState>()
    val uistate: LiveData<UiState> = _uistate

    fun getTrivia() {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            _uistate.value = UiState.Error(throwable)
        }) {
            _uistate.value = UiState.Loading
            val result = usecase.getTrivia(
                selectedMonthValue.get().orEmpty(),
                selectedDateValue.get().orEmpty()
            )
            _uistate.value = UiState.Success(result)
        }
    }
}
