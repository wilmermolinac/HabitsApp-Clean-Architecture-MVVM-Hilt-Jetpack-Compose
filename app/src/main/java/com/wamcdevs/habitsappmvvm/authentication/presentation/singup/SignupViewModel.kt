package com.wamcdevs.habitsappmvvm.authentication.presentation.singup

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wamcdevs.habitsapp.core.util.UiEvent
import com.wamcdevs.habitsapp.core.util.UiText
import com.wamcdevs.habitsappmvvm.R
import com.wamcdevs.habitsappmvvm.authentication.domain.use_case.login.PasswordResult
import com.wamcdevs.habitsappmvvm.authentication.domain.use_case.login.ResultValidatePassword
import com.wamcdevs.habitsappmvvm.authentication.domain.use_case.login.SignupUseCase
import com.wamcdevs.habitsappmvvm.navigation.NavigationRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signupUseCase: SignupUseCase, @ApplicationContext val context: Context
) : ViewModel() {

    var state by mutableStateOf(SignupState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SignupEvent) {
        when (event) {
            is SignupEvent.EmailInput -> {

                state = state.copy(email = event.input, emailMsgError = null)
            }

            is SignupEvent.PasswordInput -> {
                state = state.copy(password = event.input, passwordMsgError = null)
            }

            is SignupEvent.PasswordConfirmInput -> {
                state = state.copy(passwordConfirm = event.input, passwordConfirmMsgError = null)
            }

            SignupEvent.OnSignup -> {
                state = state.copy(isLoading = true)
                signup()
            }

            SignupEvent.OnSignin -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.NavigateUp)
                }
            }

            SignupEvent.OnLogin -> {
                state = state.copy(showDialog = false)

                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Navigate(route = NavigationRoute.Home.route))
                }
            }
        }
    }

    private fun signup() {

        val isEmailValid = signupUseCase.validateEmail(state.email)
        if (isEmailValid) {
            state = state.copy(emailMsgError = null)
        } else {
            state = state.copy(
                emailMsgError = UiText.StringResource(R.string.message_error_email),
                isLoading = false
            )
        }

        val isPasswordResult = signupUseCase.validatePassword(state.password)
        when (isPasswordResult) {
            is PasswordResult.Invalid -> {

                state = state.copy(passwordMsgError = isPasswordResult.message, isLoading = false)
            }

            PasswordResult.Valid -> {
                state = state.copy(passwordMsgError = null)
            }
        }

        val isPasswordConfirmResult =
            signupUseCase.validatePasswordConfirm(state.password, state.passwordConfirm)
        when (isPasswordConfirmResult) {
            is ResultValidatePassword.Invalid -> {
                state = state.copy(
                    passwordConfirmMsgError = isPasswordConfirmResult.message, isLoading = false
                )
            }

            ResultValidatePassword.Valid -> {
                state = state.copy(passwordConfirmMsgError = null)
            }
        }


        if (isEmailValid && isPasswordResult == PasswordResult.Valid && isPasswordConfirmResult == ResultValidatePassword.Valid) {

            viewModelScope.launch {
                signupUseCase.signup(state.email, state.password)
                    // Si el registro es exitoso
                    .onSuccess {
                        state = state.copy(showDialog = true, isLoading = false)
                    }
                    // Si fallo el registro
                    .onFailure {
                        val message =
                            it.message ?: UiText.StringResource(R.string.message_error_unknown)
                                .asString(context)
                        _uiEvent.send(UiEvent.ShowSnackBar(UiText.DynamicString(message)))
                    }
            }
        }

    }
}