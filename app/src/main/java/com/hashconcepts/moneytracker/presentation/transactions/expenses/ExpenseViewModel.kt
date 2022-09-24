package com.hashconcepts.moneytracker.presentation.transactions.expenses

import androidx.lifecycle.ViewModel
import com.hashconcepts.moneytracker.common.UtilMethods
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*

/**
 * @created 23/09/2022 - 6:29 PM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */

//@HiltViewModel
class ExpenseViewModel: ViewModel() {
    val currencySymbol = UtilMethods.fetchCurrency(Locale.UK)
}