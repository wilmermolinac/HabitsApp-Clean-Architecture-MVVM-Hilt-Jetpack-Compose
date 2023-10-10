package com.wamcdevs.habitsappmvvm.onboarding.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.wamcdevs.habitsappmvvm.onboarding.data.repository.OnboardingRepositoryImpl
import com.wamcdevs.habitsappmvvm.onboarding.domain.repository.OnboardingRepository
import com.wamcdevs.habitsappmvvm.onboarding.domain.use_case.CompleteOnboarding
import com.wamcdevs.habitsappmvvm.onboarding.domain.use_case.HasSeenOnboarding
import com.wamcdevs.habitsappmvvm.onboarding.domain.use_case.OnboardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OnboardingModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences("habits_onboarding_sharedpref", MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideOnboardingRepository(sharedPreferences: SharedPreferences): OnboardingRepository {
        return OnboardingRepositoryImpl(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideOnboardingUseCase(repository: OnboardingRepository): OnboardingUseCase {
        return OnboardingUseCase(
            hasSeenOnboarding = HasSeenOnboarding(repository),
            completeOnboarding = CompleteOnboarding(repository)
        )
    }
}