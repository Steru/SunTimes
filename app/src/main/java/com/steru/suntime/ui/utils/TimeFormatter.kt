package com.steru.suntime.ui.utils

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class TimeFormatter {

    private val hoursMinutesFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    private val amPmFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
    private val secondFormat = SimpleDateFormat("ss", Locale.getDefault())

    /**
     * Format date string from full date to 24-hour localised format
     */
    fun formatTo24hLocalTime(fullDate: String): String? {
        try {
            amPmFormat.parse(fullDate)?.let {
                return hoursMinutesFormat.format(it)
            }
        } catch (e: ParseException) {
            handleParseException(e)
        }

        return null
    }

    /**
     * Format seconds string to 24-hour format
     */
    fun formatSecondsToHours(date: String): String? {
        try {
            secondFormat.parse(date)?.let {
                return hoursMinutesFormat.format(it)
            }
        } catch (e: ParseException) {
            handleParseException(e)
        }

        return null
    }


    private fun handleParseException(e: ParseException) {
        //todo there may be a need of returning some error state
        // but for now, just a log and null string will do
        Log.e("TimeFormatter", "Parse exception: ${e.message}")
    }
}