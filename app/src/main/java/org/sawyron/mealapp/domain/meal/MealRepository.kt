package org.sawyron.mealapp.domain.meal

interface MealRepository {
    suspend fun getMealById(id: Long): Meal
}