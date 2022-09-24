package com.hashconcepts.moneytracker.presentation.transactions.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hashconcepts.moneytracker.ui.theme.Dark100
import com.hashconcepts.moneytracker.ui.theme.Light100
import com.hashconcepts.moneytracker.ui.theme.Light20
import com.hashconcepts.moneytracker.ui.theme.fonts

/**
 * @created 24/09/2022 - 3:33 AM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EnterAmountComponent(
    modifier: Modifier = Modifier,
    hint: String,
    value: String,
    currencySymbol: String,
    onValueChange: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        modifier = modifier
            .background(color = Color.Transparent)
            .padding(horizontal = 23.dp),
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = hint,
                style = MaterialTheme.typography.body2,
                fontSize = 64.sp,
                color = Light100
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            }
        ),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Light100,
            textColor = Light100,
            backgroundColor = Color.Transparent,
        ),
        textStyle = TextStyle.Default.copy(
            fontSize = 64.sp,
            fontFamily = fonts,
            fontWeight = FontWeight.Normal,
            color = Light100,
        ),
        leadingIcon = {
            Text(
                text = currencySymbol,
                style = MaterialTheme.typography.body1,
                fontSize = 64.sp,
                color = Light100,
            )
        },
    )
}