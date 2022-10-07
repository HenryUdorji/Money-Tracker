package com.hashconcepts.moneytracker.presentation.transactions.expenses

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
import com.hashconcepts.moneytracker.common.Constants
import com.hashconcepts.moneytracker.common.components.*
import com.hashconcepts.moneytracker.common.showDatePickerDialog
import com.hashconcepts.moneytracker.common.showTimePickerDialog
import com.hashconcepts.moneytracker.data.CategoryData
import com.hashconcepts.moneytracker.presentation.transactions.components.*
import com.hashconcepts.moneytracker.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

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
    val dropDownSheetState =
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

        FormFieldSection(bottomSheetState, dropDownSheetState, coroutineScope, viewModel)
    }

    var filePickerOption by remember { mutableStateOf("") }
    FilePickerBottomSheetDialog(
        bottomSheetState = bottomSheetState,
        onCameraClicked = {
            filePickerOption = Constants.FILE_PICKER_OPTION_CAMERA
            coroutineScope.launch {
                bottomSheetState.hide()
            }
        },
        onGalleryClicked = {
            filePickerOption = Constants.FILE_PICKER_OPTION_GALLERY
            coroutineScope.launch {
                bottomSheetState.hide()
            }
        },
        onDocumentClicked = {
            filePickerOption = Constants.FILE_PICKER_OPTION_DOCUMENT
            coroutineScope.launch {
                bottomSheetState.hide()
            }
        }
    )

    DropDownBottomSheet(
        bottomSheetState = dropDownSheetState,
        dropDownItems = CategoryData.categories
    ) {
        //TODO -> SHOW DIALOG TO CREATE CATEGORY
    }

    if (filePickerOption.isNotEmpty()) {
        FilePickerPermissionChecker(
            filePickerOption = filePickerOption,
            onFilePickerLaunchResult = {
                Timber.d("FILE URI -> $it")
            },
            onFilePickerLaunchResultList = {
                Timber.d("FILE URI -> $it")
            }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ColumnScope.FormFieldSection(
    bottomSheetState: ModalBottomSheetState,
    dropDownSheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope,
    viewModel: ExpenseViewModel
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
        CustomDropDown(
            hint = stringResource(R.string.category),
            label = "Subscription",
            tint = Green100,
            onDropDownClicked = {
                coroutineScope.launch {
                    dropDownSheetState.show()
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        var description by remember { mutableStateOf("") }
        CustomTextField(
            modifier = Modifier.fillMaxWidth(),
            hint = stringResource(R.string.description),
            value = description,
            onValueChange = { description = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomDropDown(
            hint = stringResource(R.string.wallet),
            label = "Paypal",
            tint = Blue100,
            onDropDownClicked = {
                coroutineScope.launch {
                    dropDownSheetState.show()
                }
            }
        )

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
        val date = remember { mutableStateOf(viewModel.todayDate) }
        val time = remember { mutableStateOf(viewModel.currentTime) }
        CustomDateTimeField(
            date = date.value,
            time = time.value,
            onDateClicked = {
                context.showDatePickerDialog {
                    date.value = it!!
                }
            },
            onTimeClicked = {
                context.showTimePickerDialog {
                    time.value = it!!
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
