package dev.cmanrg.habitsapp.authentication.domain.usecase

import dev.cmanrg.habitsapp.authentication.domain.repositoy.AuthenticationRepository

class LogoutUseCase(private val repository: AuthenticationRepository) {

    suspend operator fun invoke() {
        return repository.logout()
    }
}