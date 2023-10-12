package com.wamcdevs.habitsappmvvm.authentication.presentation.login

import com.wamcdevs.habitsapp.core.util.UiText

data class LoginState(
    val email: String = "",
    val emailMsgError: UiText? = null,
    val password: String = "",
    val passwordMsgError: UiText? = null,
    val isLoading: Boolean = false,
    val forgotPassword:String = "",
    val forgotPasswordMsgError: UiText? = null,
    val showForgotDialog:Boolean = false
)
