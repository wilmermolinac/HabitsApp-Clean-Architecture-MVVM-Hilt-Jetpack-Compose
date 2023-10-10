package com.wamcdevs.habitsappmvvm.onboarding.domain.repository

interface OnboardingRepository {

    fun hasSeenOnboarding(): Boolean

    fun completeOnboarding()
}