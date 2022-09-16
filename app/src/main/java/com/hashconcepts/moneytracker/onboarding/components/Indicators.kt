package com.hashconcepts.moneytracker.onboarding.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.hashconcepts.moneytracker.ui.theme.Violet100
import com.hashconcepts.moneytracker.ui.theme.Violet20

/**
 * @created 15/09/2022 - 11:59 PM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */
@Composable
fun Indicators(
    modifier: Modifier = Modifier,
    size: Int,
    index: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier
    ) {
        repeat(size) {
            Indicator(isSelected = index == it)
        }
    }
}

@Composable
fun Indicator(
    isSelected: Boolean
) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 16.dp else 8.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )
    val height = animateDpAsState(
        targetValue = if (isSelected) 16.dp else 8.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )

    Box(
        modifier = Modifier
            .height(height.value)
            .width(width.value)
            .clip(CircleShape)
            .background(if (isSelected) Violet100 else Violet20)
    )
}