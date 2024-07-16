package org.sawyron.mealapp.data.meals.remote

import org.sawyron.mealapp.domain.meal.Meal
import org.sawyron.mealapp.domain.meal.MealRepository
import org.sawyron.themealdbapi.TheMealDbApi
import org.sawyron.themealdbapi.createMealApi

class MealRepositoryImpl(private val api: TheMealDbApi) : MealRepository {
    companion object {
        private const val MAX_INGREDIENTS_COUNT = 20

        private fun Map<String, String?>.toMealDomain(): Meal {
            val ingredients = mutableMapOf<String, String>()
            for (i in 1..MAX_INGREDIENTS_COUNT) {
                val ingredient = this["strIngredient$i"]
                if (!ingredient.isNullOrBlank()) {
                    ingredients[ingredient] = this["strMeasure$i"] ?: ""
                }
            }
            return Meal(
                id = this["idMeal"]?.toLong() ?: throw Exception("No id for meal is provided"),
                ingredients = ingredients,
                name = this["strMeal"] ?: "",
                category = this["strCategory"] ?: "",
                area = this["strArea"] ?: "",
                imageUrl = this["strMealThumb"] ?: "",
                instructions = this["strInstructions"] ?: "",
                youtubeUrl = this["strYoutube"] ?: ""
            )
        }
    }

    constructor() : this(createMealApi("https://www.themealdb.com/api/json/v1/1/"))

    override suspend fun getMealById(id: Long): Meal {
        val dataMap = api.getMealById(id).meals[0]
        return dataMap.toMealDomain()
    }
}