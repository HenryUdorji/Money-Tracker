package com.hashconcepts.moneytracker.data

import com.hashconcepts.moneytracker.R
import com.hashconcepts.moneytracker.domain.entities.Category
import com.hashconcepts.moneytracker.domain.entities.TransactionType

/**
 * @created 17/09/2022 - 9:41 PM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */
object CategoryData {
    val categories = listOf(
        Category(icon = R.drawable.profile_image, name = "Rent", type = TransactionType.Expense, tint = 0xFFFD3C4A),
        Category(icon = R.drawable.profile_image, name = "Fee", type = TransactionType.Expense, tint = 0xFFFD3C4A),
        Category(icon = R.drawable.profile_image, name = "Tax", type = TransactionType.Expense, tint = 0xFFFD3C4A),
        Category(icon = R.drawable.profile_image, name = "Insurance", type = TransactionType.Expense, tint = 0xFFFD3C4A),
        Category(icon = R.drawable.profile_image, name = "Education", type = TransactionType.Expense, tint = 0xFFFD3C4A),
    )
}