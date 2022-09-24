package com.hashconcepts.moneytracker.common

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import java.text.SimpleDateFormat
import java.util.*

/**
 * @created 24/09/2022 - 9:21 AM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */

fun AppCompatActivity.showDatePickerDialog(onDatePicked: (String?) -> Unit) {
    val datePicker = MaterialDatePicker.Builder.datePicker().build()
    datePicker.show(this.supportFragmentManager, datePicker.toString())
    datePicker.addOnPositiveButtonClickListener { milliseconds ->
        onDatePicked(dateFormatter(milliseconds))
    }
}

fun AppCompatActivity.showTimePickerDialog(onTimePicked: (String?) -> Unit) {
    val timePicker = MaterialTimePicker.Builder().build()
    timePicker.show(this.supportFragmentManager, timePicker.toString())
    timePicker.addOnPositiveButtonClickListener {

    }
}

fun dateFormatter(milliseconds : Long?) : String?{
    milliseconds?.let{
        val formatter = SimpleDateFormat("MMM dd, yyyy", Locale.US)
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = it
        return formatter.format(calendar.time)
    }
    return null
}