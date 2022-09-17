package com.hashconcepts.moneytracker.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hashconcepts.moneytracker.data.MoneyTrackerDao
import com.hashconcepts.moneytracker.data.MoneyTrackerDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/**
 * @created 15/09/2022 - 10:01 PM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */
@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("money-tracker-pref", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): MoneyTrackerDatabase {
        return MoneyTrackerDatabase(context)
    }

    @Provides
    @Singleton
    fun provideMoneyTrackerDao(moneyTrackerDatabase: MoneyTrackerDatabase): MoneyTrackerDao {
        return moneyTrackerDatabase.moneyTrackerDao()
    }
}