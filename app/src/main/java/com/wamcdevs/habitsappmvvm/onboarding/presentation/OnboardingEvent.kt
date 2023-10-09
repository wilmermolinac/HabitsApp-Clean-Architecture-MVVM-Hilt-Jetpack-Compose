package com.wamcdevs.habitsappmvvm.onboarding.presentation

sealed class OnboardingEvent{

    object OnSkip: OnboardingEvent()
    object OnNext:OnboardingEvent()
    object OnGetStarted:OnboardingEvent()
}
