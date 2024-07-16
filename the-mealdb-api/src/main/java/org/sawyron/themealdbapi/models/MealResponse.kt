package org.sawyron.themealdbapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealResponse(@SerialName("meals") val meals: List<Map<String, String>>)
