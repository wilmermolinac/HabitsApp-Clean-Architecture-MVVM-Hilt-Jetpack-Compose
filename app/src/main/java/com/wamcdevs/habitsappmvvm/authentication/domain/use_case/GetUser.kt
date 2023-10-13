package com.wamcdevs.habitsappmvvm.authentication.domain.use_case

import com.google.firebase.auth.FirebaseUser
import com.wamcdevs.habitsappmvvm.authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class GetUser @Inject constructor(private val repository: AuthenticationRepository){
    operator fun invoke():FirebaseUser?{
        return repository.getUser()
    }
}