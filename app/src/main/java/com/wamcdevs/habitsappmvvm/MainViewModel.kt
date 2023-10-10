package com.wamcdevs.habitsappmvvm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.wamcdevs.habitsappmvvm.onboarding.domain.use_case.OnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val onboardingUseCase: OnboardingUseCase) : ViewModel() {

    var stateHasSeenOnboarding by mutableStateOf(onboardingUseCase.hasSeenOnboarding())
        private set

    init {
        stateHasSeenOnboarding = onboardingUseCase.hasSeenOnboarding()
    }
}