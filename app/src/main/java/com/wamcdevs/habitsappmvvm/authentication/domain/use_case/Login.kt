package com.wamcdevs.habitsappmvvm.authentication.domain.use_case

import com.wamcdevs.habitsappmvvm.authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class Login @Inject constructor(private val repository: AuthenticationRepository) {

    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return repository.login(email, password)
    }
}