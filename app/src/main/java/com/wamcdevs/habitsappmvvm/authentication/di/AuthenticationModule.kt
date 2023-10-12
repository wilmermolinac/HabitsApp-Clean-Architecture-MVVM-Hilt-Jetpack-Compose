package com.wamcdevs.habitsappmvvm.authentication.di

import com.google.firebase.auth.FirebaseAuth
import com.wamcdevs.habitsappmvvm.authentication.data.matcher.EmailMatcherImpl
import com.wamcdevs.habitsappmvvm.authentication.data.repository.AuthenticationRepositoryImpl
import com.wamcdevs.habitsappmvvm.authentication.domain.matcher.EmailMatcher
import com.wamcdevs.habitsappmvvm.authentication.domain.repository.AuthenticationRepository
import com.wamcdevs.habitsappmvvm.authentication.domain.use_case.login.ForgotPassword
import com.wamcdevs.habitsappmvvm.authentication.domain.use_case.login.Login
import com.wamcdevs.habitsappmvvm.authentication.domain.use_case.login.LoginUseCase
import com.wamcdevs.habitsappmvvm.authentication.domain.use_case.login.ValidateEmail
import com.wamcdevs.habitsappmvvm.authentication.domain.use_case.login.ValidatePassword
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {

    @Provides
    @Singleton
    fun provideAuthenticationRepository(firebaseAuth: FirebaseAuth): AuthenticationRepository {
        return AuthenticationRepositoryImpl(firebaseAuth)
    }


    @Provides
    @Singleton
    fun provideEmailMatcher(): EmailMatcher {
        return EmailMatcherImpl()
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(
        repository: AuthenticationRepository, emailMatcher: EmailMatcher
    ): LoginUseCase {
        return LoginUseCase(
            login = Login(repository),
            validateEmail = ValidateEmail(emailMatcher),
            validatePassword = ValidatePassword(),
            forgotPassword = ForgotPassword(repository)
        )
    }
}