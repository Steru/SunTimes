package com.steru.suntime.ui.utils

import android.util.Log
import java.lang.NumberFormatException
import java.time.DateTimeException
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

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

    val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    /**
     * Format date string from UTC full date to 24-hour localised format
     */
    fun formatTo24hLocalTime(fullDate: String): String? {
        try {
            this.fullDateFormatUTC.parse(fullDate)?.let {
                return hoursMinutesFormatLocal.format(it)
            }
        } catch (e: DateTimeParseException) {
            handleParseException(e)
        }

        return null
    }

    /**
     * Format seconds-of-day string to 24-hour format
     *
     * Allows values up to (24 * 3600) - 1
     */
    fun formatSecondCountToHours(secondCount: String): String? {
        try {
            return hoursMinutesFormatLocal.format(getTimeFromSecondCount(secondCount))
        } catch (e: Exception) {
            when (e) {
                is DateTimeException,
                is NumberFormatException -> handleParseException(e)
                else -> throw e
            }
        }

        return null
    }

    /**
     * Format full date / time string to just date
     */
    fun formatFullDateTimeToDate(date: String): String? {
        try {
            return dateFormat.format(fullDateFormatUTC.parse(date))
        } catch (e: DateTimeParseException) {
            handleParseException(e)
        }
        return null
    }

    private fun getTimeFromSecondCount(secondCount: String): LocalTime? {
        return LocalTime.ofSecondOfDay(Integer.parseInt(secondCount).toLong())
    }

    private fun handleParseException(e: Exception) {
        //todo there may be a need of returning some error state
        // but for now, just a log and null string will do
        Log.e("TimeFormatter", "Parse exception: ${e.message}")
    }
}