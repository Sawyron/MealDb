package org.sawyron.mealapp.domain.meal

data class Meal(
    val id: Long,
    val name: String,
    val category: String,
    val area: String,
    val instructions: String,
    val imageUrl: String,
    val youtubeUrl: String,
    val ingredients: Map<String, String> = mapOf()
)