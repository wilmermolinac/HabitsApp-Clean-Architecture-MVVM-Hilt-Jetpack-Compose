package com.wamcdevs.habitsappmvvm.authentication.domain.use_case.login

import com.wamcdevs.habitsapp.core.util.UiText
import com.wamcdevs.habitsappmvvm.R

class ValidatePassword {
    operator fun invoke(password:String):PasswordResult{

        if (password.length <= 5){
            return PasswordResult.Invalid(UiText.StringResource(R.string.message_error_length_password))
        }

        if (!password.any {
            it.isLowerCase()
            }){
            return PasswordResult.Invalid(UiText.StringResource(R.string.message_error_lowercase_password))
        }

        if (!password.any {
            it.isUpperCase()
            }){
            return PasswordResult.Invalid(UiText.StringResource(R.string.message_error_uppercase_password))
        }


        if (!password.any {
            it.isDigit()
            }){
            return PasswordResult.Invalid(UiText.StringResource(R.string.message_error_isnumber_password))
        }


        // Y si es valida retornamos Valid
        return PasswordResult.Valid

    }


}

sealed class PasswordResult(){
    data class Invalid(val message:UiText): PasswordResult()
    object Valid: PasswordResult()
}