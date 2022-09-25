package com.hashconcepts.moneytracker.presentation.transactions.expenses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hashconcepts.moneytracker.common.UtilMethods
import com.hashconcepts.moneytracker.data.MoneyTrackerDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

/**
 * @created 23/09/2022 - 6:29 PM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val moneyTrackerDao: MoneyTrackerDao
): ViewModel() {
    val currencySymbol = UtilMethods.fetchCurrency(Locale.UK)
    val todayDate = UtilMethods.getTodayDate(System.currentTimeMillis())
    val currentTime = UtilMethods.getCurrentTime(System.currentTimeMillis())

    suspend fun getCategories() = viewModelScope.launch {
       moneyTrackerDao.getCategories()
    }
}