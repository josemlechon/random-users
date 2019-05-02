package com.jml.random.users.common.extensions


import com.jml.random.users.common.helper.loadTimeZone
import org.junit.Assert.assertEquals
import org.junit.Test


internal class DateExtensionsKtTest {

    init {
        loadTimeZone()
    }

    @Test
    fun `validate date string change format`() {

        //Given
        val dateToFormat = "2019-05-02T13:30:00Z"
        //When
        val resultDate = dateToFormat.formattedDate(FormatDates.DATE_FULL, FormatDates.DATE_MONTH_YEAR)
        //then
        assertEquals("failure - strings are not equal", "02/05/2019", resultDate)
    }


    @Test
    fun `validate time string change format`() {

        //Given
        val dateToFormat = "2019-05-02T13:30:00"
        //When
        val resultDate = dateToFormat.formattedTime(FormatTimes.DATE_FULL, FormatTimes.TIME)
        //then
        assertEquals("failure - strings are not equal", "13:30", resultDate)
    }
}