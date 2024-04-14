package dev.cmanrg.habitsapp.authentication.data.matcher

import android.util.Patterns
import dev.cmanrg.habitsapp.authentication.domain.matcher.EmailMatcher

class EmailMatcherImpl : EmailMatcher {
    override fun isValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}