package com.wamcdevs.habitsappmvvm.authentication.domain.use_case

import com.wamcdevs.habitsappmvvm.authentication.domain.matcher.EmailMatcher
import javax.inject.Inject

class ValidateEmail @Inject constructor(private val emailMatcher: EmailMatcher) {
    operator fun invoke(email: String): Boolean {
        return emailMatcher.isValid(email)
    }
}