package com.wamcdevs.habitsappmvvm.authentication.presentation.singup

import com.wamcdevs.habitsapp.core.util.UiText

data class SignupState(
    val email: String = "",
    val emailMsgError: UiText? = null,
    val password: String = "",
    val passwordMsgError: UiText? = null,
    val passwordConfirm: String = "",
    val passwordConfirmMsgError: UiText? = null,
    val isLoading: Boolean = false,
    val showDialog: Boolean = false
)
