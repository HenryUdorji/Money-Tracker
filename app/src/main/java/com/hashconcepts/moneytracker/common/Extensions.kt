package com.hashconcepts.moneytracker.common

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.hashconcepts.moneytracker.common.UtilMethods.dateFormatter

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
    val materialTimePicker = MaterialTimePicker.Builder().build()
    materialTimePicker.show(this.supportFragmentManager, materialTimePicker.toString())
    materialTimePicker.addOnPositiveButtonClickListener {
        val pickedHour: Int = materialTimePicker.hour
        val pickedMinute: Int = materialTimePicker.minute

        // check for single digit hour hour and minute
        // and update TextView accordingly
        val formattedTime: String = when {
            pickedHour > 12 -> {
                if (pickedMinute < 10) {
                    "${materialTimePicker.hour - 12}:0${materialTimePicker.minute} PM"
                } else {
                    "${materialTimePicker.hour - 12}:${materialTimePicker.minute} PM"
                }
            }
            pickedHour == 12 -> {
                if (pickedMinute < 10) {
                    "${materialTimePicker.hour}:0${materialTimePicker.minute} PM"
                } else {
                    "${materialTimePicker.hour}:${materialTimePicker.minute} PM"
                }
            }
            pickedHour == 0 -> {
                if (pickedMinute < 10) {
                    "${materialTimePicker.hour + 12}:0${materialTimePicker.minute} AM"
                } else {
                    "${materialTimePicker.hour + 12}:${materialTimePicker.minute} AM"
                }
            }
            else -> {
                if (pickedMinute < 10) {
                    "${materialTimePicker.hour}:0${materialTimePicker.minute} AM"
                } else {
                    "${materialTimePicker.hour}:${materialTimePicker.minute} AM"
                }
            }
        }
        onTimePicked(formattedTime)
    }
}

fun String.formatAmount(): String {
    return String.format("%1$,.0f", this.toDouble())
}

fun String.formatAmountWithCurrency(): String {
    return "$"+this.formatAmount()
}