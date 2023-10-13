package com.wamcdevs.habitsappmvvm.authentication.domain.use_case

import com.wamcdevs.habitsappmvvm.authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class ForgotPassword @Inject constructor(private val repository: AuthenticationRepository) {

    suspend operator fun invoke(email:String):Result<Unit>{
        return repository.forgotPassword(email)
    }
}