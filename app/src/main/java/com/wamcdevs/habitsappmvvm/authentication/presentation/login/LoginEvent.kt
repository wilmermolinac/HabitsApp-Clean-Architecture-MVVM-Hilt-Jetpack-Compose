package com.wamcdevs.habitsappmvvm.authentication.presentation.login

sealed class LoginEvent{

    data class EmailInput(val input:String): LoginEvent()
    data class PasswordInput(val input: String):LoginEvent()
    data class ForgotPasswordInput(val input: String):LoginEvent()

    object OnLogin:LoginEvent()
    object OnForgotPassword:LoginEvent()

    object OnDismissRequestDialog:LoginEvent()
    object OnForgotPasswordBtnConfirm:LoginEvent()
    object OnSignUp:LoginEvent()
}
