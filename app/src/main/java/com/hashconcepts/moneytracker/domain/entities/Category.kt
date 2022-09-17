package com.hashconcepts.moneytracker.domain.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @created 17/09/2022 - 9:10 PM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */
@Entity
data class Category(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val icon: Int,
    val type: TransactionType
)
