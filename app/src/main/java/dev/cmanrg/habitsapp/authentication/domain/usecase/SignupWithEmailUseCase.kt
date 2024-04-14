package dev.cmanrg.habitsapp.authentication.domain.usecase

import dev.cmanrg.habitsapp.authentication.domain.repositoy.AuthenticationRepository

class SignupWithEmailUseCase(private val repository: AuthenticationRepository) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return repository.signup(email, password)
    }
}