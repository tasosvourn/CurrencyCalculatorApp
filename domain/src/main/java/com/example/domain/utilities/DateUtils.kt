package com.example.domain.utilities

import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*

/** The DateUtils class is used to parse and format date */
object DateUtils {

    fun getStringDate(timestamp: Long?): String? {
        timestamp?.let {
            return SimpleDateFormat("dd/MM/yyyy", Locale.US).format(Date(it * 1000L))
        }
        return null
    }
}