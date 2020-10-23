package com.steru.suntime.data.model

/**
 * Class modeling the sunrise-sunset API response
 */
data class SunResponse(val results: SunData, val status: String )

/**
 * Class modelling the sun data
 */
data class SunData(val sunrise: String, val sunset: String)