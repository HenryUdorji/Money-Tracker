package com.hashconcepts.moneytracker.presentation.transactions.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hashconcepts.moneytracker.ui.theme.Dark100
import com.hashconcepts.moneytracker.ui.theme.Light20

/**
 * @created 24/09/2022 - 3:07 AM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */

@Composable
fun CustomDateTimeField(
    onDateClicked: () -> Unit,
    onTimeClicked: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .border(
                    width = 1.dp,
                    color = Light20,
                    shape = RoundedCornerShape(16.dp)
                )
                .height(56.dp)
                .padding(horizontal = 10.dp)
                .clickable { onDateClicked() }
        ) {
            Text(
                text = "Sep 24, 2022",
                style = MaterialTheme.typography.h6,
                fontSize = 18.sp,
                color = Dark100,
                textAlign = TextAlign.Center,
            )
        }

        Spacer(modifier = Modifier.width(20.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .border(
                    width = 1.dp,
                    color = Light20,
                    shape = RoundedCornerShape(16.dp)
                )
                .height(56.dp)
                .padding(horizontal = 10.dp)
                .clickable { onTimeClicked() }
        ) {
            Text(
                text = "2:46 AM",
                style = MaterialTheme.typography.h6,
                fontSize = 18.sp,
                color = Dark100,
                textAlign = TextAlign.Center,
            )
        }
    }
}