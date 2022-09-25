package com.hashconcepts.moneytracker.common.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hashconcepts.moneytracker.R
import com.hashconcepts.moneytracker.ui.theme.Light100

/**
 * @created 23/09/2022 - 7:25 PM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */

@Composable
fun CustomToolbar(
    title: String,
    onBackClicked: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp, start = 15.dp, end = 15.dp)
    ) {
        IconButton(onClick = { onBackClicked() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back_arrow),
                contentDescription = null
            )
        }

        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            fontSize = 18.sp,
            color = Light100,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_back_arrow),
            contentDescription = null,
            tint = Color.Transparent
        )
    }
}