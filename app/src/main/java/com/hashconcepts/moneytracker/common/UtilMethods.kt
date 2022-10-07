package com.hashconcepts.moneytracker.common

import java.text.SimpleDateFormat
import java.util.*

/**
 * @created 24/09/2022 - 4:24 AM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */
object UtilMethods {

    fun fetchCurrency(locale: Locale): String {
        return Currency.getInstance(locale).symbol
    }

    fun dateFormatter(milliseconds : Long?) : String? {
        milliseconds?.let{
            val formatter = SimpleDateFormat("MMM dd, yyyy", Locale.US)
            val calendar: Calendar = Calendar.getInstance()
            calendar.timeInMillis = it
            return formatter.format(calendar.time)
        }
        return null
    }

    private fun timeFormatter(milliseconds: Long): String {
        val formatter = SimpleDateFormat("HH:mm aa", Locale.US)
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = milliseconds
        return formatter.format(calendar.time)
    }

    fun getTodayDate(milliseconds : Long): String {
        return dateFormatter(milliseconds)!!
    }

    fun getCurrentTime(milliseconds: Long): String {
        return timeFormatter(milliseconds)
    }

    fun provideDocumentMimeTypes(): Array<String> {
        return arrayOf(
            "image/*",
            "application/pdf",
            "application/msword",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            "application/vnd.ms-excel",
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
            "application/vnd.ms-powerpoint",
            "application/vnd.openxmlformats-officedocument.presentationml.presentation",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.template",
            "application/vnd.openxmlformats-officedocument.spreadsheetml.template",
            "application/vnd.openxmlformats-officedocument.presentationml.template",
            "application/vnd.openxmlformats-officedocument.presentationml.slideshow"
        )
    }
}