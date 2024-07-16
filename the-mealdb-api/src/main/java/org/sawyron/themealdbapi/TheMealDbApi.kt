package org.sawyron.themealdbapi

import org.sawyron.themealdbapi.models.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Details [here](https://www.themealdb.com/api.php)
 */
interface TheMealDbApi {
    @GET("lookup.php")
    suspend fun getMealById(@Query("i") id: Long): MealResponse
}