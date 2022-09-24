package com.hashconcepts.moneytracker.presentation.transactions.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hashconcepts.moneytracker.R
import com.hashconcepts.moneytracker.ui.theme.Light100
import com.hashconcepts.moneytracker.ui.theme.Violet100
import com.hashconcepts.moneytracker.ui.theme.Violet20
import com.hashconcepts.moneytracker.ui.theme.Violet40

/**
 * @created 31/07/2022 - 10:42 PM
 * @project ComposeInstagramClone
 * @author  ifechukwu.udorji
 */

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilePickerBottomSheetDialog(
    bottomSheetState: ModalBottomSheetState,
    onCameraClicked: () -> Unit,
    onGalleryClicked: () -> Unit,
    onDocumentClicked: () -> Unit,
) {
    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetBackgroundColor = Light100,
        sheetContent = {
            SheetContent(
                onCameraClicked = { onCameraClicked() },
                onGalleryClicked = { onGalleryClicked() },
                onDocumentClicked = { onDocumentClicked() },
            )
        }) {}
}

@Composable
fun SheetContent(
    onCameraClicked: () -> Unit,
    onGalleryClicked: () -> Unit,
    onDocumentClicked: () -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)) {
        Box(
            modifier = Modifier
                .width(36.dp)
                .height(4.dp)
                .background(color = Violet40, shape = RoundedCornerShape(2.dp))
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 40.dp)
        ) {
            FilePickerOption(
                icon = R.drawable.ic_camera,
                label = stringResource(R.string.camera)
            ) {
                onCameraClicked()
            }

            FilePickerOption(
                icon = R.drawable.ic_gallery,
                label = stringResource(R.string.gallery)
            ) {
                onGalleryClicked()
            }

            FilePickerOption(
                icon = R.drawable.ic_file,
                label = stringResource(R.string.document)
            ) {
                onDocumentClicked()
            }
        }
    }
}

@Composable
fun FilePickerOption(
    modifier: Modifier = Modifier,
    icon: Int,
    label: String,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Violet20)
            .height(91.dp)
            .width(107.dp)
            .clickable { onClick() }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = label, style = MaterialTheme.typography.body2, color = Violet100)
        }
    }
}
