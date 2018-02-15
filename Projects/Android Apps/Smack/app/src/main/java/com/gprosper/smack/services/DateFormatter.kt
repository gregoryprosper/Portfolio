package com.gprosper.smack.services

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by gprosper on 2/8/18.
 */
object DateFormatter {
    val iosFormatter: SimpleDateFormat
        get() {
            return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }
        }

    val localDateFormatter: SimpleDateFormat
        get() {
            return SimpleDateFormat("E, h:mm a", Locale.getDefault())
        }
}