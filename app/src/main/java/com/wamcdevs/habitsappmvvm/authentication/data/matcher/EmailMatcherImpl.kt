package com.wamcdevs.habitsappmvvm.authentication.data.matcher

import android.util.Patterns
import com.wamcdevs.habitsappmvvm.authentication.domain.matcher.EmailMatcher

class EmailMatcherImpl : EmailMatcher {

    override fun isValid(email: String): Boolean {
        // Validamos que el email tyenga un patron correcto string@string.string
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}