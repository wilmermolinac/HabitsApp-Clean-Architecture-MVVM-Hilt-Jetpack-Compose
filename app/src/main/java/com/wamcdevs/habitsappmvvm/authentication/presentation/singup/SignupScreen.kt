package com.wamcdevs.habitsappmvvm.authentication.presentation.singup

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.wamcdevs.habitsapp.core.util.UiEvent
import com.wamcdevs.habitsappmvvm.R
import com.wamcdevs.habitsappmvvm.authentication.presentation.singup.components.SignupComponents
import com.wamcdevs.habitsappmvvm.core.presentation.components.HabitsTopBar
import kotlinx.coroutines.flow.collect

@Composable
fun SignupScreen(onNavigate: (UiEvent) -> Unit, viewModel: SignupViewModel = hiltViewModel()) {

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val context = LocalContext.current

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
                        message = event.message.asString(context), duration = SnackbarDuration.Short
                    )
                }
            }


        }
    })


    Scaffold(topBar = {
        HabitsTopBar(isNavigate = true,
            title = stringResource(id = R.string.register),
            onNavigate = { onNavigate(UiEvent.NavigateUp) })
    }, snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)

    }) {
        SignupComponents(paddingValues = it)
    }
}