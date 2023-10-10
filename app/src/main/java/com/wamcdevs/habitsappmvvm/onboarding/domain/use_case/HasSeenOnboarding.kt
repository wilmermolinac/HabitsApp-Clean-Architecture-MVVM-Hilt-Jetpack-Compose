package com.wamcdevs.habitsappmvvm.onboarding.domain.use_case

import com.wamcdevs.habitsappmvvm.onboarding.domain.repository.OnboardingRepository
import javax.inject.Inject

class HasSeenOnboarding @Inject constructor(private val repository: OnboardingRepository) {

    operator fun invoke():Boolean{
        return repository.hasSeenOnboarding()
    }
}