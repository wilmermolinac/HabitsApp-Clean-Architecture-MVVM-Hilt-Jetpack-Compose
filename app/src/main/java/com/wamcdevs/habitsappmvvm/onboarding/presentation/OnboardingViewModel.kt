package com.wamcdevs.habitsappmvvm.onboarding.presentation

import androidx.lifecycle.ViewModel
import com.wamcdevs.habitsapp.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(): ViewModel() {

    // Creamos el canal de eventos de la ui
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: OnboardingEvent){
        when(event){
            OnboardingEvent.OnNext -> TODO()
            OnboardingEvent.OnSkip -> TODO()
            OnboardingEvent.OnGetStarted -> TODO()
        }
    }


}