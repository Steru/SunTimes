package com.steru.suntime.data.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val SUN_REPO_BASE_URL = "https://api.sunrise-sunset.org/"

/**
 * Client for accessing the sun retrofit service
 */
object SunApiClient {

    private fun getRetrofit() = Retrofit.Builder()
            .baseUrl(SUN_REPO_BASE_URL)
            .addConverterFactory(
                    GsonConverterFactory.create(
                            GsonBuilder().create()))
            .build()

    /**
     * service for fetching the sun data
     */
    val sunService: SunService by lazy {
        getRetrofit().create(SunService::class.java)
    }
}