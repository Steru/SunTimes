package com.steru.suntime.ui.utils

import android.util.Log
import java.text.ParseException
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Class used to format time strings.
 */
class TimeFormatter {

    private val hoursMinutesFormat = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())
    private val fullDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.getDefault())
    private val secondFormat = DateTimeFormatter.ofPattern("ss", Locale.getDefault())
    val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")


    /**
     * Format date string from full date to 24-hour localised format
     */
    fun formatTo24hLocalTime(fullDate: String): String? {
        try {
            this.fullDateFormat.parse(fullDate)?.let {
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

    /**
     * Format full date / time string to just date
     */
    fun formatFullDateTimeToDate(date: String): String? {
        try {
            return dateFormat.format(fullDateFormat.parse(date))
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