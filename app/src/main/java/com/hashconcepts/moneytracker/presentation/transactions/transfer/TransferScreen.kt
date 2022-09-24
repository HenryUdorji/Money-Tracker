package com.hashconcepts.moneytracker.presentation.transactions.transfer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hashconcepts.moneytracker.R
import com.hashconcepts.moneytracker.common.components.CustomToolbar
import com.hashconcepts.moneytracker.ui.theme.Blue100
import com.hashconcepts.moneytracker.ui.theme.Dark50
import com.hashconcepts.moneytracker.ui.theme.Light100
import com.hashconcepts.moneytracker.ui.theme.Red100
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * @created 23/09/2022 - 6:27 PM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */

@Destination
@Composable
fun TransferScreen(
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Blue100)
    ) {
        CustomToolbar(stringResource(id = R.string.transfer)) {
            navigator.navigateUp()
        }
    }
}