package com.steru.suntime.data.model

import com.google.gson.annotations.SerializedName

/**
 * Class modeling the sunrise-sunset API response
 */
data class SunResponse(val results: SunData, val status: String)

/**
 * Class modelling the sun data
 */
data class SunData(
    val sunrise: String,
    val sunset: String,

    @SerializedName("day_length")
    val dayLength: String,

    @SerializedName("civil_twighlight_begin")
    val civilTwilightBegin: String,

    @SerializedName("civil_twighlight_end")
    val civilTwilightEnd: String
)

/**
 * Sun data class in user-friendly format
 */
data class FormattedSunData(val sunrise: String,
                            val sunset: String)