package com.wamcdevs.habitsappmvvm.onboarding.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.wamcdevs.habitsappmvvm.R
import com.wamcdevs.habitsappmvvm.onboarding.presentation.OnboardingEvent
import com.wamcdevs.habitsappmvvm.onboarding.presentation.OnboardingPagerInfo
import com.wamcdevs.habitsappmvvm.onboarding.presentation.OnboardingViewModel

@Composable
fun OnboardingContent(paddingValues: PaddingValues, viewModel: OnboardingViewModel = hiltViewModel()) {

    // Cremos la lista de paginas del Onboarding
    val pages = listOf(
        OnboardingPagerInfo(
            title = stringResource(id = R.string.onboarding_page1_title), subtitle = stringResource(
                id = R.string.onboarding_page1_subtitle
            ), image = R.drawable.onboarding1
        ), OnboardingPagerInfo(
            title = stringResource(id = R.string.onboarding_page2_title), subtitle = stringResource(
                id = R.string.onboarding_page1_subtitle
            ), image = R.drawable.onboarding2
        ), OnboardingPagerInfo(
            title = stringResource(id = R.string.onboarding_page3_title), subtitle = stringResource(
                id = R.string.onboarding_page1_subtitle
            ), image = R.drawable.onboarding3
        ), OnboardingPagerInfo(
            title = stringResource(id = R.string.onboarding_page4_title), subtitle = stringResource(
                id = R.string.onboarding_page1_subtitle
            ), image = R.drawable.onboarding4
        )
    )

    OnboardingPager(
        modifier = Modifier.padding(paddingValues = paddingValues),
        pages = pages,
        onFinish = {viewModel.onEvent(OnboardingEvent.OnGetStarted)})
}