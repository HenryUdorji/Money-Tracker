package com.hashconcepts.moneytracker.presentation.transactions.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hashconcepts.moneytracker.R
import com.hashconcepts.moneytracker.domain.entities.Category
import com.hashconcepts.moneytracker.ui.theme.Dark100
import com.hashconcepts.moneytracker.ui.theme.Dark75
import com.hashconcepts.moneytracker.ui.theme.Light100
import com.hashconcepts.moneytracker.ui.theme.Light20

/**
 * @created 24/09/2022 - 9:36 PM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownBottomSheet(
    bottomSheetState: ModalBottomSheetState,
    dropDownItems: List<Category>
) {
    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        sheetBackgroundColor = Light100,
        sheetElevation = 20.dp,
        scrimColor = Light20.copy(alpha = 0.2f),
        sheetContent = {
            SheetContent(dropDownItems)
        }
    ) {}
}

@Composable
fun SheetContent(dropDownItems: List<Category>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Text(
            text = stringResource(R.string.select_a_category),
            style = MaterialTheme.typography.h4,
            fontSize = 16.sp,
            color = Dark75
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(dropDownItems) { dropDownItem ->
                DropDownItem(dropDownItem = dropDownItem)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(56.dp)
                .border(width = 1.dp, color = Light20, shape = RoundedCornerShape(5.dp))
                .padding(horizontal = 10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add_circle),
                contentDescription = null,
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = stringResource(R.string.add_new_category),
                style = MaterialTheme.typography.h4,
                color = Dark75,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun DropDownItem(dropDownItem: Category) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = dropDownItem.icon),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .background(shape = CircleShape, color = Color.Transparent)
        )

        Text(text = dropDownItem.name, style = MaterialTheme.typography.body2, color = Dark100)
    }
}