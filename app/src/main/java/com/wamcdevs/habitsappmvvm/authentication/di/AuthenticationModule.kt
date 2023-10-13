package com.wamcdevs.habitsappmvvm.authentication.di

import com.google.firebase.auth.FirebaseAuth
import com.wamcdevs.habitsappmvvm.authentication.data.matcher.EmailMatcherImpl
import com.wamcdevs.habitsappmvvm.authentication.data.repository.AuthenticationRepositoryImpl
import com.wamcdevs.habitsappmvvm.authentication.domain.matcher.EmailMatcher
import com.wamcdevs.habitsappmvvm.authentication.domain.repository.AuthenticationRepository
import com.wamcdevs.habitsappmvvm.authentication.domain.use_case.ForgotPassword
import com.wamcdevs.habitsappmvvm.authentication.domain.use_case.GetUser
import com.wamcdevs.habitsappmvvm.authentication.domain.use_case.Login
import com.wamcdevs.habitsappmvvm.authentication.domain.use_case.LoginUseCase
import com.wamcdevs.habitsappmvvm.authentication.domain.use_case.Signup
import com.wamcdevs.habitsappmvvm.authentication.domain.use_case.SignupUseCase
import com.wamcdevs.habitsappmvvm.authentication.domain.use_case.ValidateEmail
import com.wamcdevs.habitsappmvvm.authentication.domain.use_case.ValidatePassword
import com.wamcdevs.habitsappmvvm.authentication.domain.use_case.ValidatePasswordConfirm
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
            forgotPassword = ForgotPassword(repository),
            getUser = GetUser(repository)
        )
    }

    @Provides
    @Singleton
    fun provideSignupUseCase(
        repository: AuthenticationRepository, emailMatcher: EmailMatcher
    ): SignupUseCase {
        return SignupUseCase(
            signup = Signup(repository),
            validateEmail = ValidateEmail(emailMatcher),
            validatePassword = ValidatePassword(),
            validatePasswordConfirm = ValidatePasswordConfirm()
        )
    }
}