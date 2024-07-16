package org.sawyron.themealdbapi

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

fun createMealApi(
    baseUrl: String,
    okHttpClient: OkHttpClient? = null,
    json: Json = Json
): TheMealDbApi = createRetrofit(baseUrl, okHttpClient, json)
    .create()

private fun createRetrofit(
    baseUrl: String,
    okHttpClient: OkHttpClient?,
    json: Json
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(
            json.asConverterFactory("application/json; charset=UTF8".toMediaType())
        )
        .run { if (okHttpClient != null) client(okHttpClient) else this }
        .build()
}