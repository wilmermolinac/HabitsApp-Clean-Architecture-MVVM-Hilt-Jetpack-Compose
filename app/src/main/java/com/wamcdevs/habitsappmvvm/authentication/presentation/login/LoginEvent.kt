package com.wamcdevs.habitsappmvvm.authentication.presentation.login

sealed class LoginEvent{

    data class OnEmailInput(val input:String): LoginEvent()
    data class OnPasswordInput(val input: String):LoginEvent()

    object OnLogin:LoginEvent()
    object OnForgotPassword:LoginEvent()
    object OnSignUp:LoginEvent()
}
