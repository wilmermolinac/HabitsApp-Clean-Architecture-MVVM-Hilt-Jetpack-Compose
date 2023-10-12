package com.wamcdevs.habitsappmvvm.authentication.presentation.login

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wamcdevs.habitsapp.core.util.UiEvent
import com.wamcdevs.habitsapp.core.util.UiText
import com.wamcdevs.habitsappmvvm.R
import com.wamcdevs.habitsappmvvm.authentication.domain.use_case.login.LoginUseCase
import com.wamcdevs.habitsappmvvm.authentication.domain.use_case.login.PasswordResult
import com.wamcdevs.habitsappmvvm.navigation.NavigationRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase, @ApplicationContext private val context: Context
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailInput -> {
                state = state.copy(email = event.input, emailMsgError = null)
            }

            is LoginEvent.PasswordInput -> {
                state = state.copy(password = event.input, passwordMsgError = null)
            }

            LoginEvent.OnSignUp -> {

                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Navigate(route = NavigationRoute.Signup.route))
                }

            }
            LoginEvent.OnLogin -> {
                state = state.copy(isLoading = true)
                login()
            }

            is LoginEvent.ForgotPasswordInput -> {
                state = state.copy(forgotPassword = event.input, forgotPasswordMsgError = null)

            }

            LoginEvent.OnForgotPassword -> {
                state = state.copy(showForgotDialog = true)
            }

            LoginEvent.OnForgotPasswordBtnConfirm -> {
                forgotPassword()
            }

            LoginEvent.OnDismissRequestDialog -> {
                state = state.copy(
                    showForgotDialog = false, forgotPassword = "", forgotPasswordMsgError = null
                )
            }
        }
    }

    private fun forgotPassword() {

        val isEmailValid = loginUseCase.validateEmail(state.forgotPassword)
        if (isEmailValid) {

            viewModelScope.launch {
                loginUseCase.forgotPassword(state.forgotPassword)
                    // Si es exitoso
                    .onSuccess {
                        state = state.copy(
                            forgotPasswordMsgError = null,
                            forgotPassword = "",
                            showForgotDialog = false
                        )
                        _uiEvent.send(UiEvent.ShowSnackBar(message = UiText.StringResource(R.string.message_forgot_password)))
                    }
                    // Si hay un fallo
                    .onFailure {
                        val message =
                            it.message ?: UiText.StringResource(R.string.message_error_unknown)
                                .asString(context)

                        state = state.copy(forgotPasswordMsgError = UiText.DynamicString(message))
                    }
            }
        } else {
            state = state.copy(
                forgotPasswordMsgError = UiText.StringResource(R.string.message_error_email)
            )
        }

    }

    private fun login() {

        val isEmailValid = loginUseCase.validateEmail(state.email)
        if (isEmailValid) {
            state = state.copy(emailMsgError = null)
        } else {
            state = state.copy(
                emailMsgError = UiText.StringResource(R.string.message_error_email),
                isLoading = false
            )
        }


        val passwordResult = loginUseCase.validatePassword(state.password)
        when (passwordResult) {
            is PasswordResult.Invalid -> {
                state = state.copy(passwordMsgError = passwordResult.message, isLoading = false)
            }

            PasswordResult.Valid -> {
                Unit
            }
        }


        // Y si es valido, enviamos los datos de inicio de session a firebase para que valide
        if (isEmailValid && passwordResult == PasswordResult.Valid) {
            viewModelScope.launch {
                loginUseCase.login(email = state.email, password = state.password)
                    // Si el resultado es exitoso
                    .onSuccess {
                        // Navegamos a esta ruta
                        _uiEvent.send(UiEvent.Navigate(route = NavigationRoute.Home.route))

                    }.onFailure {

                        //Habilitamos de nuevo los botones
                        state = state.copy(isLoading = false)

                        // Si falla el login
                        val message =
                            it.message ?: UiText.StringResource(R.string.message_error_unknown)
                                .asString(context)

                        Log.d("LoginViewModel", "onFailure: $message")
                        // Mostrarmos el un error en un SnackBar
                        _uiEvent.send(UiEvent.ShowSnackBar(UiText.DynamicString(message)))

                    }
            }
        }


    }
}