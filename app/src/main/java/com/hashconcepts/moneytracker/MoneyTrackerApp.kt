package com.hashconcepts.moneytracker

import android.app.Application
import android.os.Build
import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * @created 15/09/2022 - 9:18 PM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */
@HiltAndroidApp
class MoneyTrackerApp: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            CrashReportingTree()
        }
    }

    private class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            val deviceInfo = "${Build.DEVICE}-${Build.MODEL}-${Build.MANUFACTURER}"
            FirebaseCrashlytics.getInstance().setCustomKey("deviceInfo", deviceInfo)

            if (priority >= Log.WARN) {
                FirebaseCrashlytics.getInstance().log("$tag: $message")
                t?.let { FirebaseCrashlytics.getInstance().recordException(it) }
            }
            // Do we really want to log info? Maybe
            if (priority == Log.INFO) {
                FirebaseCrashlytics.getInstance().log(message)
            }
        }
    }
}
