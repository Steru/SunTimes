package com.steru.suntime.data.repository

import com.steru.suntime.data.api.SunApiClient


/**
 * Repository used for fetching sun time data
 */
class SunRepository {
    private val retrofitClient = SunApiClient.sunService

    //todo add error handling
    suspend fun getSunriseSunsetTimes(latitude: Float, longitude: Float) =
            retrofitClient.getTimes(latitude, longitude).results
}
