package com.example.androidapptemplate.features.trivia

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.androidapptemplate.domain.features.trivia.usecase.TriviaUseCase
import com.example.androidapptemplate.util.getDateValue
import com.example.androidapptemplate.util.getMonthValue
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// layoutでObservableFieldとか参照しているので、ViewModel自体をinternalにできない.
@HiltViewModel
class TriviaSelectViewModel @Inject constructor(
    private val usecase: TriviaUseCase
) : ViewModel() {
    val selectedMonthValue = ObservableField<String>()
    val selectedDateValue = ObservableField<String>()
    val monthDropdownValues = ObservableField(getMonthValue())
    val dateDropdownValues = ObservableField(getDateValue())

    val resultTrivia = ObservableField<String>()

    internal suspend fun getTrivia() {
        val result = usecase.getTrivia(
            selectedMonthValue.get().orEmpty(),
            selectedDateValue.get().orEmpty()
        )
        resultTrivia.set(result)
    }
}