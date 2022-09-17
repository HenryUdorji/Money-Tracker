package com.hashconcepts.moneytracker.data

import com.hashconcepts.moneytracker.domain.entities.Category
import com.hashconcepts.moneytracker.domain.entities.TransactionType

/**
 * @created 17/09/2022 - 9:41 PM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */
object CategoryData {
    val categories = listOf(
        Category(icon = 0, name = "Rent", type = TransactionType.Expense),
        Category(icon = 0, name = "Fee", type = TransactionType.Expense),
        Category(icon = 0, name = "Tax", type = TransactionType.Expense),
        Category(icon = 0, name = "Insurance", type = TransactionType.Expense),
        Category(icon = 0, name = "Education", type = TransactionType.Expense),
    )
}