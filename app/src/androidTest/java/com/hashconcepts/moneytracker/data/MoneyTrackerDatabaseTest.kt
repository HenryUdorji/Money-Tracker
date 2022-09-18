package com.hashconcepts.moneytracker.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
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
            .build()

        moneyTrackerDao = moneyTrackerDatabase.moneyTrackerDao()
    }

    @After
    fun tearDown() {
        moneyTrackerDatabase.close()
    }

    @Test
    fun moneyTrackerDB_saveCategories() {
        runBlocking {
            val category = moneyTrackerDao.saveCategories(CategoryData.categories)
            assertThat(category).isNotNull()
        }
    }

    @Test
    fun moneyTrackerDB_getCategories() {
        runBlocking {
            val categories = moneyTrackerDao.getCategories()
            assertThat(categories.size).isEqualTo(5)
        }
    }
}