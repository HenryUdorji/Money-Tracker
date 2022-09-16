package com.hashconcepts.moneytracker.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hashconcepts.moneytracker.destinations.HomeScreenDestination
import com.hashconcepts.moneytracker.destinations.OnBoardingScreenDestination
import com.hashconcepts.moneytracker.ui.theme.Violet100
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay

/**
 * @created 15/09/2022 - 8:53 PM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */
@RootNavGraph(true)
@Destination
@Composable
fun SplashScreen(
    navigator: DestinationsNavigator,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        delay(500L)
        if (!viewModel.isFirstAppLaunch) {
            navigator.popBackStack()
            navigator.navigate(HomeScreenDestination)
        } else {
            navigator.popBackStack()
            navigator.navigate(OnBoardingScreenDestination)
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Violet100)) {
        Image(
            painter = painterResource(id = com.hashconcepts.moneytracker.R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .width(197.dp)
                .height(68.dp)
        )
    }
}