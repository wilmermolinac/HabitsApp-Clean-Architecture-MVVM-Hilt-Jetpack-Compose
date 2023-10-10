package com.wamcdevs.habitsappmvvm.onboarding.data.repository

import android.content.SharedPreferences
import com.wamcdevs.habitsappmvvm.onboarding.domain.repository.OnboardingRepository
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    OnboardingRepository {

    companion object {
        private const val KEY_HAS_SEEN_ONBOARDING = "has_seen_onboarding"
    }

    override fun hasSeenOnboarding(): Boolean {
        return sharedPreferences.getBoolean(KEY_HAS_SEEN_ONBOARDING, false)
    }

    override fun completeOnboarding() {
        sharedPreferences.edit().putBoolean(KEY_HAS_SEEN_ONBOARDING, true).apply()
    }
}