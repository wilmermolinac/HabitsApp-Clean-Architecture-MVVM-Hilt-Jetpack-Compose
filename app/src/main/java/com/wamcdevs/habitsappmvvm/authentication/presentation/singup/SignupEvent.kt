package com.wamcdevs.habitsappmvvm.authentication.presentation.singup

import android.app.usage.UsageEvents.Event

sealed class SignupEvent(){

    data class EmailInput(val input:String):SignupEvent()
    data class PasswordInput(val input: String):SignupEvent()

    data class PasswordConfirmInput(val input: String):SignupEvent()

    object OnSignup:SignupEvent()
    object OnSignin:SignupEvent()

    object OnLogin: SignupEvent()
}
