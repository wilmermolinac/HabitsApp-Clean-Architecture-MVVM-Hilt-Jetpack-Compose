package com.wamcdevs.habitsappmvvm.onboarding.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.wamcdevs.habitsapp.core.util.UiEvent
import com.wamcdevs.habitsappmvvm.onboarding.presentation.components.OnboardingContent
import kotlinx.coroutines.flow.collect

@Composable
fun OnboardingScreen(onNavigate: (UiEvent) -> Unit, viewModel: OnboardingViewModel = hiltViewModel()) {

    // Escucahamos el canal de eventos
    LaunchedEffect(key1 = true, block = {
        viewModel.uiEvent.collect(){event ->

            when(event){
                is UiEvent.Navigate -> {
                    onNavigate(event)
                }

                UiEvent.NavigateUp -> {
                    Unit
                }
                is UiEvent.ShowSnackBar -> {
                    Unit
                }
            }

        }
    })

    Scaffold {
        OnboardingContent(paddingValues = it)
    }

}