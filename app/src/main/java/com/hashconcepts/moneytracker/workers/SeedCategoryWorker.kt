package com.hashconcepts.moneytracker.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hashconcepts.moneytracker.data.MoneyTrackerDatabase
import com.hashconcepts.moneytracker.domain.entities.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

/**
 * @created 18/09/2022 - 7:29 PM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */

/** This worker reads Categories from asset file and saves into Room */
class SeedCategoryWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {
    @Inject
    lateinit var moneyTrackerDatabase: MoneyTrackerDatabase

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                val categoryJson = applicationContext.assets.open("category.json")
                    .bufferedReader()
                    .use { it.readText() }
                val categoryType = object : TypeToken<List<Category>>() {}.type
                val categories: List<Category> = Gson().fromJson(categoryJson, categoryType)

                moneyTrackerDatabase.moneyTrackerDao().saveCategories(categories)

                Result.success()
            } catch (e: Exception) {
                Timber.e(e.localizedMessage)
                Result.failure()
            }
        }
    }
}