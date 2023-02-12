package com.example.domain.utilities

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DateUtilsTest {

    @Before
    fun setUp() {
    }

    @Test
    fun testGetStringDate() {
        assertEquals(DateUtils.getStringDate(1519296206), "22/02/2018")
        assertEquals(DateUtils.getStringDate(null), null)
        assertEquals(DateUtils.getStringDate(0), "01/01/1970")
    }
}