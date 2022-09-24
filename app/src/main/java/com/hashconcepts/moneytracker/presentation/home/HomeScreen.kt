package com.hashconcepts.moneytracker.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hashconcepts.moneytracker.R
import com.hashconcepts.moneytracker.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination

/**
 * @created 15/09/2022 - 10:17 PM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */

@Destination
@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Light100)
    ) {
        TopSection()
    }
}

@Composable
fun TopSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    0f to TopSectionFirstColor,
                    1f to TopSectionSecondColor
                ),
                shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
            )
            .padding(horizontal = 15.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_image),
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .border(width = 1.dp, color = Violet100, shape = CircleShape)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = null,
                )
                Spacer(
                    modifier = Modifier
                        .width(4.dp)
                        .size(24.dp)
                )
                Text(
                    text = "October",
                    style = MaterialTheme.typography.h6,
                    fontSize = 14.sp,
                    color = Dark50
                )
            }

            Image(
                painter = painterResource(id = R.drawable.ic_notification),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(modifier = Modifier.height(15.dp))

        AccountBalance()

        Spacer(modifier = Modifier.height(27.dp))

        TransactionBalance()

        Spacer(modifier = Modifier.height(23.dp))
    }
}

@Composable
fun TransactionBalance() {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth()
    ) {
        TransactionBalanceItem(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            icon = R.drawable.ic_income,
            color = Green100,
            label = stringResource(R.string.income),
            amount = "$5000"
        )

        TransactionBalanceItem(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            icon = R.drawable.ic_expense,
            color = Red100,
            label = stringResource(R.string.expenses),
            amount = "$1200"
        )
    }
}

@Composable
fun TransactionBalanceItem(
    modifier: Modifier = Modifier,
    icon: Int,
    color: Color,
    label: String,
    amount: String,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(16.dp)
            .height(80.dp)
            .background(color = color, shape = RoundedCornerShape(28.dp))
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(48.dp)
                .background(shape = RoundedCornerShape(16.dp), color = Light80)
        ) {
            Icon(painter = painterResource(id = icon), contentDescription = null, tint = color)
        }
        
        Spacer(modifier = Modifier.width(10.dp))

        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.h6,
                color = Light80,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
            )

            Text(
                text = amount,
                style = MaterialTheme.typography.h1,
                color = Light80,
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun ColumnScope.AccountBalance() {
    Text(
        text = stringResource(R.string.account_balance),
        style = MaterialTheme.typography.h6,
        color = Light20,
        fontSize = 14.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )

    Text(
        text = "$300000",
        style = MaterialTheme.typography.h1,
        color = Dark75,
        fontSize = 40.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}
