package com.wamcdevs.habitsappmvvm.authentication.domain.use_case

data class LoginUseCase(
    val login: Login,
    val validateEmail: ValidateEmail,
    val validatePassword: ValidatePassword,
    val forgotPassword: ForgotPassword,
    val getUser: GetUser
)