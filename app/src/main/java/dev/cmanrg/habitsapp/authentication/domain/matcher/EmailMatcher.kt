package dev.cmanrg.habitsapp.authentication.domain.matcher

interface EmailMatcher {
    fun isValid(email: String): Boolean
}