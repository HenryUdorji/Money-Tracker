package com.hashconcepts.moneytracker.common

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
}