package com.wamcdevs.habitsappmvvm.authentication.presentation.login

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.wamcdevs.habitsapp.core.util.UiEvent
import com.wamcdevs.habitsappmvvm.authentication.presentation.login.components.LoginContent

@Composable
fun LoginScreen(onNavigate: (UiEvent) -> Unit) {

    Scaffold {
        LoginContent(paddingValues = it)
    }
}