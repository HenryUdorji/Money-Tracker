package com.hashconcepts.moneytracker.presentation.transactions.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hashconcepts.moneytracker.R
import com.hashconcepts.moneytracker.ui.theme.Light20

/**
 * @created 24/09/2022 - 2:12 AM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */

@Composable
fun CustomDropDown(
    label: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Light20, shape = RoundedCornerShape(16.dp))
            .height(56.dp)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.body1,
            fontSize = 16.sp,
            color = Light20
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_down),
            contentDescription = null,
            tint = Light20
        )
    }
}