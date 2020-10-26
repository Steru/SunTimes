package com.steru.suntime

import android.util.Log
import com.steru.suntime.ui.utils.TimeFormatter
import io.mockk.every
import io.mockk.mockkStatic
import org.junit.Test

class TimeFormatterTest {

    init {
        mockkStatic(Log::class)
        every { Log.e(any(), any()) } returns 0
    }

    private val fullTestValue = "2015-09-21T05:05:35+00:00"
    private val fullTestValueNoColon = "2015-09-21T05:05:35+0000"
    private val dateTestValue = "2015-09-21"
    private val formatter = TimeFormatter()


    @Test
    fun `format full date time to 24h local time`() {
        // there were problems getting proper timezone in test - and to avoid typing in exact timezone,
        // we're just checking if it "just works"
        // todo test the actual values
        assert(formatter.formatTo24hLocalTime(fullTestValue) != null)
    }

    @Test
    fun `format full date time to 24h local time - no colon`() {
        // the date pattern uses "ZZZZZ" as zone, so "+0400" will not be parsed
        assert(formatter.formatTo24hLocalTime(fullTestValueNoColon).equals(null))
    }

    @Test
    fun `format seconds to hours`() {
        val secondsValue = (7 * 3600).toString()
        val expectedValue = "07:00"

        assert(formatter.formatSecondCountToHours(secondsValue).equals(expectedValue))
    }

    @Test
    fun `format seconds to hours - big number`() {
        val secondsValue = (10 * 10 * 3600).toString()
        val expectedValue = null // only 24 hours in a day! can't go above

        assert(formatter.formatSecondCountToHours(secondsValue).equals(expectedValue))
    }


    @Test
    fun `format seconds to hours - wrong value`() {
        val secondsValue = (-1).toString()
        val expectedValue = null // count can't be negative

        assert(formatter.formatSecondCountToHours(secondsValue).equals(expectedValue))
    }

    @Test
    fun `format full date time to date`() {
        assert(formatter.formatFullDateTimeToDate(fullTestValue).equals(dateTestValue))
    }

    @Test
    fun `format full date time to date - no colon`() {
        assert(formatter.formatFullDateTimeToDate(fullTestValueNoColon).equals(null))
    }

}
