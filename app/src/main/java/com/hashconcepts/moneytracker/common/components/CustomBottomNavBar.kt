package com.hashconcepts.moneytracker.common.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.hashconcepts.moneytracker.R
import com.hashconcepts.moneytracker.presentation.destinations.*
import com.hashconcepts.moneytracker.ui.theme.Light80
import com.hashconcepts.moneytracker.ui.theme.UnSelectedColor
import com.hashconcepts.moneytracker.ui.theme.Violet100

/**
 * @created 04/08/2022 - 10:50 PM
 * @project ComposeInstagramClone
 * @author  ifechukwu.udorji
 */
sealed class BottomNavItem(
    val iconRes: Int?,
    val label: Int?,
    val destination: DirectionDestination?
) {
    object HomeScreen :
        BottomNavItem(R.drawable.ic_home, R.string.home, HomeScreenDestination)

    object TransactionScreen :
        BottomNavItem(R.drawable.ic_transaction, R.string.transaction, TransactionScreenDestination)

    object BudgetScreen :
        BottomNavItem(R.drawable.ic_budget, R.string.budget, BudgetScreenDestination)

    object EmptyScreen :
        BottomNavItem(null, null, null)

    object ProfileScreen :
        BottomNavItem(R.drawable.ic_user, R.string.profile, ProfileScreenDestination)
}

val bottomNavItems = listOf(
    BottomNavItem.HomeScreen,
    BottomNavItem.TransactionScreen,
    BottomNavItem.EmptyScreen,
    BottomNavItem.BudgetScreen,
    BottomNavItem.ProfileScreen,
)

@Composable
fun CustomBottomNavBar(
    navController: NavController,
    navItems: List<BottomNavItem> = bottomNavItems,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomAppBar(
        backgroundColor = Light80,
        elevation = 5.dp,
        cutoutShape = CircleShape,
        modifier = Modifier.height(70.dp)
    ) {
        navItems.forEach { item ->
            val selectedNavItem = currentRoute?.contains(item.destination?.route ?: "") == true

            BottomNavigationItem(
                selected = selectedNavItem,
                selectedContentColor = Violet100,
                unselectedContentColor = UnSelectedColor,
                alwaysShowLabel = true,
                icon = {
                    item.iconRes?.let { iconRes ->
                        Icon(
                            painter = painterResource(id = iconRes),
                            contentDescription = null
                        )
                    }
                },
                label = {
                    item.label?.let { label ->
                        Text(
                            text = stringResource(id = label),
                            style = MaterialTheme.typography.h5,
                            fontSize = 10.sp
                        )
                    }
                },
                onClick = {
                    if (!selectedNavItem) {
                        item.destination?.let { destination ->
                            navController.popBackStack()
                            navController.navigate(destination.route) {
                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                }
            )
        }
    }
}