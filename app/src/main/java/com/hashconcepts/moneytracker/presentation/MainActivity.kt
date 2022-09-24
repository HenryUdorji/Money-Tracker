package com.hashconcepts.moneytracker.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hashconcepts.moneytracker.R
import com.hashconcepts.moneytracker.common.components.CustomBottomNavBar
import com.hashconcepts.moneytracker.common.components.CustomFloatingActionMenu
import com.hashconcepts.moneytracker.presentation.destinations.*
import com.hashconcepts.moneytracker.ui.theme.MoneyTrackerTheme
import com.hashconcepts.moneytracker.ui.theme.Violet100
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navHostEngine = rememberNavHostEngine()
            val newBackStackEntry by navController.currentBackStackEntryAsState()
            val route = newBackStackEntry?.destination?.route

            val showBottomBar = route in listOf(
                HomeScreenDestination.route,
                TransactionScreenDestination.route,
                BudgetScreenDestination.route,
                ProfileScreenDestination.route
            )

            val floatTweenSpec: TweenSpec<Float> = tween(durationMillis = 1000)
            var isVisible by remember { mutableStateOf(false) }

            val animateRotateAngle by animateFloatAsState(
                targetValue = if (isVisible) 135.0f else 0f,
                animationSpec = floatTweenSpec
            )

            MoneyTrackerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    backgroundColor = MaterialTheme.colors.background,
                    bottomBar = {
                        if (showBottomBar) {
                            CustomBottomNavBar(navController = navController)
                        }
                    },
                    floatingActionButtonPosition = FabPosition.Center,
                    isFloatingActionButtonDocked = true,
                    floatingActionButton = {
                        if (showBottomBar) {
                            FloatingActionButton(
                                shape = CircleShape,
                                backgroundColor = Violet100,
                                onClick = { isVisible = !isVisible }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_add),
                                    contentDescription = null,
                                    tint = Color.Unspecified,
                                    modifier = Modifier.rotate(animateRotateAngle)
                                )
                            }
                        }
                    },
                ) {
                    DestinationsNavHost(
                        navGraph = NavGraphs.root,
                        navController = navController,
                        engine = navHostEngine
                    )

                    Box(modifier = Modifier.fillMaxSize()) {
                        CustomFloatingActionMenu(
                            isVisible = isVisible,
                            onExpensesClicked = {
                                isVisible = !isVisible
                                navController.navigate(ExpenseScreenDestination.route)
                            },
                            onIncomeClicked = {
                                isVisible = !isVisible
                                navController.navigate(IncomeScreenDestination.route)
                            },
                            onTransferClicked = {
                                isVisible = !isVisible
                                navController.navigate(TransferScreenDestination.route)
                            },
                        )
                    }
                }
            }
        }
    }
}