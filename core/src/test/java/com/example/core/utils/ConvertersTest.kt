package com.example.core.utils

import org.junit.Assert.*

import org.junit.Test

class ConvertersTest {

    @Test
    fun testDateConverter() {
        // Given a dt from calling API
        val dt = 1729323335L

        // When converting to date string to give us the expected format
        val result = Converters.dateConverter(dt)

        // Then the result should match the expected date string
        assertEquals("2024-10-19", result)
    }

    @Test
    fun testConvertDateToDay() {
        // Given a date string
        val dateString = "2024-10-18"

        // When converting to day of the week
        val result = Converters.convertDateToDay(dateString)

        // Then the result should match the expected day
        assertEquals("Friday", result)
    }

    @Test
    fun testCapitalizeFirstLetter_alreadyCapitalized() {
        // Given a string with the first letter is lower case
        val day = "tuesday"

        // When capitalize First Letter
        val result = Converters.capitalizeFirstLetter(day)

        // Then the result should start with upper case -> "Tuesday"
        assertEquals("Tuesday", result)
    }
}