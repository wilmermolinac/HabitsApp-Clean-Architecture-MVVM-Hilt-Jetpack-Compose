package com.wamcdevs.habitsappmvvm.authentication.domain.use_case.login

import com.wamcdevs.habitsapp.core.util.UiText
import com.wamcdevs.habitsappmvvm.R

class ValidatePasswordConfirm {
    operator fun invoke(password: String, passwordConfirm: String): ResultValidatePassword {

        if (password == passwordConfirm) {
            return ResultValidatePassword.Valid
        } else {
            return ResultValidatePassword.Invalid(message = UiText.StringResource(R.string.message_error_confirmpassword))
        }

    }
}

sealed class ResultValidatePassword {
    data class Invalid(val message: UiText) : ResultValidatePassword()
    object Valid : ResultValidatePassword()
}