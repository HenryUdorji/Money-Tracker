package com.hashconcepts.moneytracker.presentation.transactions.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hashconcepts.moneytracker.R
import com.hashconcepts.moneytracker.ui.theme.Light20

/**
 * @created 24/09/2022 - 2:22 AM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */

@Composable
fun CustomDottedBorderField(
    modifier: Modifier = Modifier
) {
    val stroke =
        Stroke(width = 2f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f))

    Box(contentAlignment = Alignment.Center, modifier = modifier.height(56.dp)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRoundRect(
                color = Light20,
                style = stroke,
                cornerRadius = CornerRadius(16.dp.toPx())
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_attachment),
                contentDescription = null,
                tint = Light20,
                modifier = Modifier.rotate(218f)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = stringResource(R.string.add_attachment),
                style = MaterialTheme.typography.body2,
                color = Light20
            )
        }
    }
}