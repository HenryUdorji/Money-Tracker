package com.hashconcepts.moneytracker.presentation.onboarding

import androidx.lifecycle.ViewModel
import com.hashconcepts.moneytracker.data.SharedPrefUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @created 15/09/2022 - 9:16 PM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */
@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val sharedPrefUtil: SharedPrefUtil
): ViewModel() {

    fun saveFirstAppLaunch() = sharedPrefUtil.saveFirstAppLaunch()

    val isFirstAppLaunch = sharedPrefUtil.isFirstAppLaunch()
}