package org.sawyron.mealapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sawyron.mealapp.domain.meal.Meal
import org.sawyron.mealapp.domain.meal.MealRepository

class MainViewModel(private val mealRepository: MealRepository) : ViewModel() {
    private val _mealState = MutableStateFlow(
        Meal(
            id = 0,
            name = "",
            category = "",
            area = "",
            instructions = "",
            imageUrl = "",
            youtubeUrl = ""
        )
    )
    val mealState: StateFlow<Meal> = _mealState

    private var fetchJob: Job? = null

    fun fetchMeal() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            val fetchedMeal = mealRepository.getMealById(52772)
            _mealState.value = fetchedMeal
        }
    }
}