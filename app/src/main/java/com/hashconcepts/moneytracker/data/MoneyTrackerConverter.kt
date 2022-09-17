package com.hashconcepts.moneytracker.data

import androidx.room.TypeConverter
import com.hashconcepts.moneytracker.domain.entities.TransactionType

/**
 * @created 17/09/2022 - 9:37 PM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */
class MoneyTrackerConverter {

    @TypeConverter
    fun fromTransactionType(transactionType: TransactionType): String {
        return transactionType.name
    }

    @TypeConverter
    fun toTransactionType(transactionType: String): TransactionType {
        return TransactionType.valueOf(transactionType)
    }
}