package com.steru.suntime.data.api

import com.steru.suntime.data.model.SunResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Service for accessing the sunrise-sunset API endpoints
 */
interface SunService {

    /**
     * Get sunrise and sunset times
     *
     * @param latitude location coordinate
     * @param longitude location coordinate
     */
    @GET("json")
    suspend fun getTimes(
        @Query("lat") latitude: Float,
        @Query("lng") longitude: Float,
        @Query("formatted") formatted: Int = 0
    ) : SunResponse
}
