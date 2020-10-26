package com.steru.suntime.ui.utils

import android.util.Log
import java.text.ParseException
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * Class used to format time strings.
 */
class TimeFormatter {

    private val hoursMinutesFormatLocal = DateTimeFormatter
        .ofPattern("HH:mm")
        .withZone(ZoneId.systemDefault())

    private val fullDateFormatUTC = DateTimeFormatter
        .ofPattern("yyyy-MM-dd'T'HH:mm:ssZZZZZ")
        .withZone(ZoneId.of("UTC"))

    private val secondFormat = DateTimeFormatter.ofPattern("ss")

    val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")


    /**
     * Format date string from UTC full date to 24-hour localised format
     */
    fun formatTo24hLocalTime(fullDate: String): String? {
        try {
            this.fullDateFormatUTC.parse(fullDate)?.let {
                return hoursMinutesFormatLocal.format(it)
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
                return hoursMinutesFormatLocal.format(it)
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
            return dateFormat.format(fullDateFormatUTC.parse(date))
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