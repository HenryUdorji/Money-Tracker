package com.hashconcepts.moneytracker.data

import android.content.SharedPreferences
import com.hashconcepts.moneytracker.common.Constants
import javax.inject.Inject

/**
 * @created 15/09/2022 - 9:59 PM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */
class SharedPrefUtil @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    fun saveFirstAppLaunch() {
        sharedPreferences.edit().putBoolean(Constants.FIRST_APP_LAUNCH, false).apply()
    }

    fun isFirstAppLaunch(): Boolean {
        return sharedPreferences.getBoolean(Constants.FIRST_APP_LAUNCH, true)
    }
}