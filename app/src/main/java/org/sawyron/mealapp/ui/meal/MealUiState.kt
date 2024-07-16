package org.sawyron.mealapp.ui.meal

data class MealUiState(
    val isFetching: Boolean = false,
    val name: String,
    val category: String,
    val imgUrl: String,
    val youtubeUrl: String)
