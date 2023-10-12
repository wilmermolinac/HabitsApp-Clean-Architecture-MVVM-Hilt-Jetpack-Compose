package com.wamcdevs.habitsappmvvm.authentication.presentation.login

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.wamcdevs.habitsapp.core.util.UiEvent
import com.wamcdevs.habitsappmvvm.authentication.presentation.login.components.LoginContent
import kotlinx.coroutines.flow.collect

@Composable
fun LoginScreen(onNavigate: (UiEvent) -> Unit, viewModel: LoginViewModel = hiltViewModel()) {

    val context = LocalContext.current

    val snackbarHostState = remember {
        SnackbarHostState()
    }


    LaunchedEffect(key1 = true, block = {
        viewModel.uiEvent.collect() { event ->

            when (event) {
                is UiEvent.Navigate -> {
                    onNavigate(event)
                }

                UiEvent.NavigateUp -> {
                    onNavigate(event)
                }

                is UiEvent.ShowSnackBar -> {

                    snackbarHostState.showSnackbar(
                        message = event.message.asString(context),
                        duration = SnackbarDuration.Long
                    )
                }
            }

        }
    })


    Scaffold(snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    }) {
        LoginContent(paddingValues = it)
    }
}