package com.hashconcepts.moneytracker.common.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hashconcepts.moneytracker.ui.theme.Light100
import com.hashconcepts.moneytracker.ui.theme.Light80
import com.hashconcepts.moneytracker.ui.theme.Violet100

/**
 * @created 16/09/2022 - 12:08 AM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomRaisedButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Button(
        modifier = modifier.height(56.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Violet100),
        shape = RoundedCornerShape(size = 16.dp),
        onClick = {
            keyboardController?.hide()
            onClick()
        }
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.button,
            color = Light80,
            fontSize = 18.sp,
            modifier = Modifier.padding(vertical = 7.dp)
        )
    }
}