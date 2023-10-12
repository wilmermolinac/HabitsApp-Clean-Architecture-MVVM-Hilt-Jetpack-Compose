package com.wamcdevs.habitsappmvvm.authentication.domain.matcher

interface EmailMatcher {
    fun isValid(email:String):Boolean
}