package dev.cmanrg.habitsapp.authentication.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cmanrg.habitsapp.authentication.domain.usecase.LoginUseCases
import dev.cmanrg.habitsapp.authentication.presentation.login.util.PasswordErrorParser
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserCases: LoginUseCases
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChange -> {
                state = state.copy(
                    email = event.email
                )
            }

            LoginEvent.Login -> {
                login()
            }

            is LoginEvent.PasswordChange -> {
                state = state.copy(
                    password = event.password
                )
            }

            LoginEvent.SignUp -> {
                state = state.copy(
                    signUp = true
                )
            }
        }
    }

    private fun login() {
        state = state.copy(
            emailError = null,
            passwordError = null
        )

        if (!loginUserCases.validateEmailUseCase(state.email)) {
            state = state.copy(
                emailError = "El email no es valido"
            )
        }

        val passwordResult = loginUserCases.validatePasswordUseCase(state.password)
        state = state.copy(
            passwordError = PasswordErrorParser.parseError(passwordResult)
        )

        if (state.emailError == null && state.passwordError == null){
            state = state.copy(
                isLoading = true
            )

            viewModelScope.launch {
                loginUserCases.loginWithEmailUseCase(state.email,state.password).onSuccess {
                    state = state.copy(
                        isLoggedIn = true
                    )
                }.onFailure {
                    state = state.copy(
                        emailError = it.message
                    )
                }
                state = state.copy(
                    isLoading = false
                )
            }

        }
    }
}