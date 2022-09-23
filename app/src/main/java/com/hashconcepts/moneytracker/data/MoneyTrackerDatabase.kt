package com.hashconcepts.moneytracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.hashconcepts.moneytracker.domain.entities.Category
import com.hashconcepts.moneytracker.workers.SeedCategoryWorker
import kotlinx.coroutines.*
import kotlinx.coroutines.internal.synchronized
import javax.inject.Inject

/**
 * @created 17/09/2022 - 8:52 PM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */
@Database(
    entities = [
        Category::class,
    ],
    exportSchema = true,
    version = 1
)
@TypeConverters(MoneyTrackerConverter::class)
abstract class MoneyTrackerDatabase : RoomDatabase() {

    abstract fun moneyTrackerDao(): MoneyTrackerDao

    companion object {
        lateinit var moneyTrackerDatabase: MoneyTrackerDatabase

        @OptIn(InternalCoroutinesApi::class)
        operator fun invoke(context: Context, workManager: WorkManager): MoneyTrackerDatabase {
            if (!this::moneyTrackerDatabase.isInitialized) {
                synchronized(this) {
                    if (!this::moneyTrackerDatabase.isInitialized) {
                        moneyTrackerDatabase = Room.databaseBuilder(
                            context,
                            MoneyTrackerDatabase::class.java,
                            "moneytrackerdb"
                        )
                            .addCallback(object : Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                    //Save Categories
                                    val workRequest =
                                        OneTimeWorkRequestBuilder<SeedCategoryWorker>().build()
                                    workManager.enqueue(workRequest)
                                }
                            })
                            .fallbackToDestructiveMigrationOnDowngrade()
                            .build()
                    }
                }
            }
            return moneyTrackerDatabase
        }
    }
}