package com.hashconcepts.moneytracker.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * @created 17/09/2022 - 11:02 PM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */
@RunWith(AndroidJUnit4::class)
class MoneyTrackerDatabaseTest {
    private lateinit var moneyTrackerDao: MoneyTrackerDao
    private lateinit var moneyTrackerDatabase: MoneyTrackerDatabase


    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        moneyTrackerDatabase = Room.inMemoryDatabaseBuilder(
            context,
            MoneyTrackerDatabase::class.java
        )
//            .addCallback(object : RoomDatabase.Callback() {
//                override fun onCreate(db: SupportSQLiteDatabase) {
//                    super.onCreate(db)
//                    runBlocking {
//                        moneyTrackerDao.saveCategories(CategoryData.categories)
//                    }
//                }
//            })
            .build()

        moneyTrackerDao = moneyTrackerDatabase.moneyTrackerDao()
    }

    @After
    fun tearDown() {
        moneyTrackerDatabase.close()
    }

    @Test
    fun moneyTrackerDB_getCategories() {
        runBlocking {
            moneyTrackerDao.saveCategories(CategoryData.categories)
            val categories = moneyTrackerDao.getCategories()
            assertThat(categories.size, `is`(5))
        }
    }
}