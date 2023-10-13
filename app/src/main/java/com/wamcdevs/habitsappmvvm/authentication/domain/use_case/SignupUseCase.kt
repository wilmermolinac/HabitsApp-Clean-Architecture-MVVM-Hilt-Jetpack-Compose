package com.wamcdevs.habitsappmvvm.authentication.domain.use_case

data class SignupUseCase(
    val signup: Signup,
    val validateEmail: ValidateEmail,
    val validatePassword: ValidatePassword,
    val validatePasswordConfirm: ValidatePasswordConfirm
)
