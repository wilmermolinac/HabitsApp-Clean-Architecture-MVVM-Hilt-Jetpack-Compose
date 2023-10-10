package com.wamcdevs.habitsappmvvm.onboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wamcdevs.habitsapp.core.util.UiEvent
import com.wamcdevs.habitsappmvvm.navigation.NavigationRoute
import com.wamcdevs.habitsappmvvm.onboarding.domain.use_case.OnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(private val onboardingUseCase: OnboardingUseCase) :
    ViewModel() {

    // Creamos el canal de eventos de la ui
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: OnboardingEvent) {
        when (event) {

            OnboardingEvent.OnGetStarted -> {
                onboardingUseCase.completeOnboarding()

                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Navigate(route = NavigationRoute.Login.route))
                }
            }
        }
    }


}