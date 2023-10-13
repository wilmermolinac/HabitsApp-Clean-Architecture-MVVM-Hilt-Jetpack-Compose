package com.wamcdevs.habitsappmvvm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.wamcdevs.habitsapp.core.util.UiEvent
import com.wamcdevs.habitsappmvvm.authentication.domain.use_case.LoginUseCase
import com.wamcdevs.habitsappmvvm.onboarding.domain.use_case.OnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val onboardingUseCase: OnboardingUseCase, private val loginUseCase: LoginUseCase
) : ViewModel() {


    //      Si el id de usuario es diferente de null,
    //      significa que hay un usuario logueado
    var isLoggedIn by mutableStateOf<Boolean>(loginUseCase.getUser()?.uid != null)
        private set


    var stateHasSeenOnboarding by mutableStateOf(onboardingUseCase.hasSeenOnboarding())
        private set

    init {
        stateHasSeenOnboarding = onboardingUseCase.hasSeenOnboarding()
    }
}