package dev.cmanrg.habitsapp.onboarding.domain.usecase

import dev.cmanrg.habitsapp.onboarding.domain.repository.OnboardingRepository

// LOS USE CASE UNICAMENTE DEBE DE TENER UNICAMENTE UN METODO Y EL RESTO DEBE DE SER PRIVADO
class CompleteOnboardingUseCase(
    private val repository: OnboardingRepository
) {

    operator fun invoke() {
        repository.completeOnboarding()
    }
}