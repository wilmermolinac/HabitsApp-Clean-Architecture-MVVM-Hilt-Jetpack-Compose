package com.wamcdevs.habitsappmvvm.authentication.domain.use_case.login

data class SignupUseCase(
    val signup: Signup,
    val validateEmail: ValidateEmail,
    val validatePassword: ValidatePassword,
    val validatePasswordConfirm: ValidatePasswordConfirm
)
