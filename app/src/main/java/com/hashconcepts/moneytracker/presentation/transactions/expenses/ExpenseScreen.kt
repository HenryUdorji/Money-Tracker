package com.hashconcepts.moneytracker.presentation.transactions.expenses

import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hashconcepts.moneytracker.R
import androidx.hilt.navigation.compose.hiltViewModel
import com.hashconcepts.moneytracker.common.components.*
import com.hashconcepts.moneytracker.common.showDatePickerDialog
import com.hashconcepts.moneytracker.common.showTimePickerDialog
import com.hashconcepts.moneytracker.presentation.transactions.components.*
import com.hashconcepts.moneytracker.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*

/**
 * @created 23/09/2022 - 6:23 PM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun ExpenseScreen(
    navigator: DestinationsNavigator,
    viewModel: ExpenseViewModel = hiltViewModel()
) {
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Red100)
    ) {
        CustomToolbar(stringResource(id = R.string.expenses)) {
            navigator.navigateUp()
        }

        Spacer(modifier = Modifier.height(60.dp))

        HowMuchSection(viewModel)

        Spacer(modifier = Modifier.height(16.dp))

        FormFieldSection(bottomSheetState, coroutineScope)
    }

    FilePickerBottomSheetDialog(
        bottomSheetState = bottomSheetState,
        onCameraClicked = {
            coroutineScope.launch {
                bottomSheetState.hide()
            }
        },
        onGalleryClicked = {
            coroutineScope.launch {
                bottomSheetState.hide()
            }
        },
        onDocumentClicked = {
            coroutineScope.launch {
                bottomSheetState.hide()
            }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ColumnScope.FormFieldSection(
    bottomSheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .weight(1f)
            .background(
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                color = Light100
            )
            .padding(vertical = 24.dp, horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        CustomDropDown(label = stringResource(R.string.category))

        Spacer(modifier = Modifier.height(16.dp))

        var description by remember { mutableStateOf("") }
        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            hint = stringResource(R.string.description),
            value = description,
            onValueChange = { description = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomDropDown(label = stringResource(R.string.wallet))

        Spacer(modifier = Modifier.height(16.dp))

        CustomDottedBorderField(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                coroutineScope.launch {
                    bottomSheetState.show()
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        val context = LocalContext.current as AppCompatActivity
        CustomDateTimeField(
            onDateClicked = {
                context.showDatePickerDialog {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            },
            onTimeClicked = {
                context.showTimePickerDialog {

                }
            }
        )

        Spacer(modifier = Modifier.height(40.dp))

        CustomRaisedButton(
            text = stringResource(id = R.string.continue_text),
            modifier = Modifier.fillMaxWidth()
        ) {

        }
    }
}

@Composable
fun ColumnScope.HowMuchSection(viewModel: ExpenseViewModel) {
    Text(
        text = stringResource(R.string.how_much),
        style = MaterialTheme.typography.body1,
        fontSize = 18.sp,
        color = Light80,
        modifier = Modifier.padding(horizontal = 25.dp)
    )

    var amount by remember { mutableStateOf("") }
    EnterAmountComponent(
        hint = "0",
        value = amount,
        currencySymbol = viewModel.currencySymbol,
        onValueChange = { amount = it },
        modifier = Modifier.fillMaxWidth()
    )
}
