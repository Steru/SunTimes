package com.steru.suntime.data.repository

import com.steru.suntime.data.api.SunApiClient
import com.steru.suntime.data.model.SunData
import com.steru.suntime.ui.utils.Resource
import retrofit2.HttpException
import java.lang.Exception
import java.net.SocketTimeoutException


/**
 * Repository used for fetching sun time data
 */
class SunRepository {
    private val retrofitClient = SunApiClient.sunService

    /**
     * Get sun times from retrofit client
     * Catch the possible exception and pass it in a Resource object
     *
     * @return Resource containing the data
     */
    suspend fun getSunriseSunsetTimes(date: String, latitude: Float, longitude: Float) : Resource<SunData> {
        return try {
            val response = retrofitClient.getTimes(latitude, longitude, date).results
            Resource.success(response)
        } catch (e: Exception) {
            handleException(e)
        }
    }

    private fun handleException(e: Exception): Resource<SunData> {
        val msg = when (e) {
            is HttpException -> "Http error ${e.code()}"
            is SocketTimeoutException -> "Socket timeout error"
            else -> "Unknown error ${e.message} \nAre you doing something suspicious?"
        }

        return Resource.error(msg)
    }
}
