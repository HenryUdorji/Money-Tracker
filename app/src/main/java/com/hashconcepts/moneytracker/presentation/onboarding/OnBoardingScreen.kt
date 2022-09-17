package com.hashconcepts.moneytracker.presentation.onboarding

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.hashconcepts.moneytracker.R
import com.hashconcepts.moneytracker.common.components.CustomRaisedButton
import com.hashconcepts.moneytracker.destinations.HomeScreenDestination
import com.hashconcepts.moneytracker.presentation.onboarding.components.Indicators
import com.hashconcepts.moneytracker.ui.theme.Dark50
import com.hashconcepts.moneytracker.ui.theme.Light100
import com.hashconcepts.moneytracker.ui.theme.Light20
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * @created 15/09/2022 - 8:52 PM
 * @project Money Tracker
 * @author  ifechukwu.udorji
 */
@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Destination
@Composable
fun OnBoardingScreen(
    navigator: DestinationsNavigator,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Light100)
    ) {
        HorizontalPager(
            count = onBoardingScreens.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            val onBoardingScreen = onBoardingScreens[pagerState.currentPage]
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = onBoardingScreen.icon),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = stringResource(id = onBoardingScreen.title),
                    style = MaterialTheme.typography.h1,
                    fontSize = 32.sp,
                    color = Dark50,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = stringResource(id = onBoardingScreen.description),
                    style = MaterialTheme.typography.h1,
                    fontSize = 16.sp,
                    color = Light20,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Indicators(
            size = onBoardingScreens.size,
            index = pagerState.currentPage,
            modifier = Modifier
                .padding(20.dp)
                .align(Alignment.CenterHorizontally)
        )

        val visible = pagerState.currentPage == onBoardingScreens.size - 1
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(initialOffsetY = { fullHeight -> fullHeight }),
        ) {
            CustomRaisedButton(
                text = stringResource(R.string.continue_text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                viewModel.saveFirstAppLaunch()
                navigator.popBackStack()
                navigator.navigate(HomeScreenDestination)
            }
        }
    }
}

sealed class OnBoardingScreens(
    val icon: Int,
    val title: Int,
    val description: Int
) {
    object FirstScreen : OnBoardingScreens(
        icon = R.drawable.ic_onboarding_1,
        title = R.string.gain_total_control_of_your_money,
        description = R.string.become_your_own_manager
    )

    object SecondScreen : OnBoardingScreens(
        icon = R.drawable.ic_onboarding_2,
        title = R.string.know_where_your_money_goes,
        description = R.string.track_your_transaction_easily
    )

    object ThirdScreen : OnBoardingScreens(
        icon = R.drawable.ic_onboarding_3,
        title = R.string.planning_ahead,
        description = R.string.setup_your_budget
    )
}

val onBoardingScreens = listOf(
    OnBoardingScreens.FirstScreen,
    OnBoardingScreens.SecondScreen,
    OnBoardingScreens.ThirdScreen,
)