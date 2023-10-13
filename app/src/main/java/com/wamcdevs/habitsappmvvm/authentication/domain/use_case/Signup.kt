package com.wamcdevs.habitsappmvvm.authentication.domain.use_case

import com.wamcdevs.habitsappmvvm.authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class Signup @Inject constructor(private val repository: AuthenticationRepository) {

    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return repository.signup(email, password)
    }
}