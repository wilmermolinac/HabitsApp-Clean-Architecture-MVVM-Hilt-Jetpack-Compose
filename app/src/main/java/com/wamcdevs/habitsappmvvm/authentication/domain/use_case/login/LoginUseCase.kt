package com.wamcdevs.habitsappmvvm.authentication.domain.use_case.login

data class LoginUseCase(
    val login: Login,
    val validateEmail: ValidateEmail,
    val validatePassword: ValidatePassword,
    val forgotPassword: ForgotPassword
)