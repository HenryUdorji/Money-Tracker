package com.hashconcepts.moneytracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hashconcepts.moneytracker.domain.entities.Category
import kotlinx.coroutines.*
import kotlinx.coroutines.internal.synchronized
import timber.log.Timber

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
abstract class MoneyTrackerDatabase: RoomDatabase() {

    abstract fun moneyTrackerDao(): MoneyTrackerDao

    companion object {
        lateinit var moneyTrackerDatabase: MoneyTrackerDatabase

        @OptIn(InternalCoroutinesApi::class, DelicateCoroutinesApi::class)
        operator fun invoke(context: Context): MoneyTrackerDatabase {
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
                                    GlobalScope.launch(Dispatchers.IO) {
                                        try {
                                            moneyTrackerDatabase.moneyTrackerDao().saveCategories(CategoryData.categories)
                                        } catch (e: Exception) {
                                            Timber.e(e.localizedMessage)
                                        }
                                    }
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