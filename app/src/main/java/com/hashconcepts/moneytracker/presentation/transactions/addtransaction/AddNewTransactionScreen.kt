package com.hashconcepts.moneytracker.presentation.transactions.addtransaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hashconcepts.moneytracker.ui.theme.Dark50
import com.hashconcepts.moneytracker.ui.theme.Light100
import com.ramcosta.composedestinations.annotation.Destination

/**
 * @created 15/09/2022 - 10:17 PM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */

@Destination
@Composable
fun AddNewTransactionScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Light100)
    ) {
        Text(
            text = "Add Transaction Screen", style = MaterialTheme.typography.h3, color = Dark50,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}