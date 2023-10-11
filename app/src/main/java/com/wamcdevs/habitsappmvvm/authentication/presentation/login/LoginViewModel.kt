package com.wamcdevs.habitsappmvvm.authentication.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.wamcdevs.habitsapp.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailInput -> {
                state = state.copy(email = event.input)
            }

            is LoginEvent.OnPasswordInput -> {
                state = state.copy(password = event.input)
            }


            LoginEvent.OnForgotPassword -> {}
            LoginEvent.OnSignUp -> {}
            LoginEvent.OnLogin -> {
                state = state.copy(isLoading = true)
            }
        }
    }

}