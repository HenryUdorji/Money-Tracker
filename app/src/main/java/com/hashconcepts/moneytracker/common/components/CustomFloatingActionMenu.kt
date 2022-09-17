package com.hashconcepts.moneytracker.common.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.hashconcepts.moneytracker.R
import com.hashconcepts.moneytracker.ui.theme.*

/**
 * @created 17/09/2022 - 5:16 PM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */
private const val duration: Int = 1000
private val intOffsetTweenSpec: TweenSpec<IntOffset> = tween(durationMillis = duration)

@Composable
fun BoxScope.CustomFloatingActionMenu(isVisible: Boolean) {
    val density = LocalDensity.current

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { with(density) { 316.dp.roundToPx() } },
            animationSpec = intOffsetTweenSpec,
        ), exit = slideOutVertically(
            targetOffsetY = { with(density) { 316.dp.roundToPx() } },
            animationSpec = intOffsetTweenSpec,
        ),
        modifier = Modifier
            .height(316.dp)
            .align(Alignment.BottomCenter)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .height(316.dp)
                .background(
                    brush = Brush.verticalGradient(
                        0.5f to Violet20,
                        0.9f to Violet20.copy(alpha = 0.3f),
                        0.99f to Violet20.copy(alpha = 0.005f),
                        startY = Float.POSITIVE_INFINITY,
                        endY = 0.0f,
                    )
                )
        ) {
            FabItem(
                icon = R.drawable.ic_money_exchange,
                backgroundColor = Blue100
            ) {

            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                FabItem(icon = R.drawable.ic_income, backgroundColor = Green100) {

                }

                FabItem(icon = R.drawable.ic_expense, backgroundColor = Red100) {

                }
            }
        }
    }
}

@Composable
fun FabItem(
    modifier: Modifier = Modifier,
    icon: Int,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    FloatingActionButton(
        shape = CircleShape,
        backgroundColor = backgroundColor,
        onClick = { onClick() },
        modifier = modifier
            .width(56.dp)
            .height(56.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.Unspecified,
        )
    }
}