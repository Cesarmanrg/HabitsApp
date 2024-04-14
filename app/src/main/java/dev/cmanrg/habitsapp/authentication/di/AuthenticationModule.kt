package dev.cmanrg.habitsapp.authentication.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.cmanrg.habitsapp.authentication.data.matcher.EmailMatcherImpl
import dev.cmanrg.habitsapp.authentication.data.repository.AuthenticationRepositoryImpl
import dev.cmanrg.habitsapp.authentication.domain.matcher.EmailMatcher
import dev.cmanrg.habitsapp.authentication.domain.repositoy.AuthenticationRepository
import dev.cmanrg.habitsapp.authentication.domain.usecase.LoginUseCases
import dev.cmanrg.habitsapp.authentication.domain.usecase.LoginWithEmailUseCase
import dev.cmanrg.habitsapp.authentication.domain.usecase.ValidateEmailUseCase
import dev.cmanrg.habitsapp.authentication.domain.usecase.ValidatePasswordUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {

    @Provides
    @Singleton
    fun provideAuthenticationRepository(): AuthenticationRepository {
        return AuthenticationRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideEmailMatcher(): EmailMatcher {
        return EmailMatcherImpl()
    }

    @Provides
    @Singleton
    fun provideSignupUseCases(
        repository: AuthenticationRepository,
        emailMatcher: EmailMatcher
    ): LoginUseCases {
        return LoginUseCases(
            loginWithEmailUseCase = LoginWithEmailUseCase(repository),
            validateEmailUseCase = ValidateEmailUseCase(emailMatcher),
            validatePasswordUseCase = ValidatePasswordUseCase()
        )
    }
}