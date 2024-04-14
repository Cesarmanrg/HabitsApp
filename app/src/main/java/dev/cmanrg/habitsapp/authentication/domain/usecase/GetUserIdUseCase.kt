package dev.cmanrg.habitsapp.authentication.domain.usecase

import dev.cmanrg.habitsapp.authentication.domain.repositoy.AuthenticationRepository

class GetUserIdUseCase(private val repository: AuthenticationRepository) {
    operator fun invoke(): String? {
        return repository.getUserId()
    }
}