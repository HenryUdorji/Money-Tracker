package com.hashconcepts.moneytracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.hashconcepts.moneytracker.domain.entities.Category

/**
 * @created 17/09/2022 - 9:03 PM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */
@Dao
interface MoneyTrackerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCategories(categories: List<Category>)
}