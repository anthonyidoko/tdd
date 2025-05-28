package com.anthony.tddpractice.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anthony.tddpractice.login.domain.model.LoginCredential
import com.anthony.tddpractice.login.domain.repository.LoginRepository
import com.anthony.tddpractice.login.domain.result.DataError
import com.anthony.tddpractice.login.domain.result.Result
import com.anthony.tddpractice.login.domain.validator.ICredentialValidator
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

private const val SCREEN_STATE = "SCREEN_STATE"

class LoginViewModel(
    private val stateHandle: SavedStateHandle,
    private val loginRepository: LoginRepository,
    private val credentialValidator: ICredentialValidator,
) : ViewModel() {

    val state: StateFlow<LoginScreenState> = stateHandle.getStateFlow(
        SCREEN_STATE,
        LoginScreenState()
    )

    fun updateUsername(newValue: String) {
        stateHandle.update<LoginScreenState>(SCREEN_STATE) {
            it.copy(username = newValue)
        }
    }

    fun updatePassword(newValue: String) {
        val previousState = stateHandle.get<LoginScreenState>(SCREEN_STATE) ?: LoginScreenState()
        stateHandle[SCREEN_STATE] = previousState.copy(
            password = newValue
        )
    }

    private inline fun <T> SavedStateHandle.update(key: String, block: (T) -> T) {
        requireNotNull(get<T>(key)).let(block).let {
            this[key] = it
        }
    }

    fun updateState() {
        stateHandle.get<LoginScreenState>(SCREEN_STATE)?.let { state ->
            if (state.username.isNotEmpty() || state.password.isNotEmpty()) {
                stateHandle[SCREEN_STATE] = LoginScreenState(
                    username = state.username,
                    password = state.password
                )
            }
        }
    }

    fun performLogin() {
        val loginScreenState = stateHandle.get<LoginScreenState>(SCREEN_STATE) ?: LoginScreenState()
        val username = loginScreenState.username
        val password = loginScreenState.password
        val loginCredentials = LoginCredential(username = username, password = password)

        if (!isUsernameValid(loginScreenState)) return

        if (!isPasswordValid(loginScreenState)) return

        loginRepository.performLogin(loginCredentials).onStart {
            stateHandle.update<LoginScreenState>(SCREEN_STATE) {
                it.copy(isLoading = true)
            }
        }.onEach { value ->
            when (value) {
                is Result.Success -> {
                    stateHandle.update<LoginScreenState>(SCREEN_STATE) {
                        it.copy(
                            message = "Login Successful"
                        )
                    }
                }

                is Result.Failure -> {
                    stateHandle.update<LoginScreenState>(SCREEN_STATE) { state ->
                        state.copy(
                            isLoginError = true,
                            message = getErrorMessage(value.error)
                        )
                    }
                }
            }
        }.onCompletion {
            stateHandle.update<LoginScreenState>(SCREEN_STATE) {
                it.copy(
                    isLoading = false
                )
            }
        }.launchIn(
            viewModelScope
        )

    }

    private fun isUsernameValid(state: LoginScreenState): Boolean {
        val isValid = state.isUsernameValid(credentialValidator)
        if (!isValid) {
            stateHandle.update<LoginScreenState>(SCREEN_STATE) { state ->
                state.copy(message = "Username is invalid")
            }
        }
        return isValid
    }

    private fun isPasswordValid(state: LoginScreenState): Boolean {
        val isValid = state.isPasswordValid(credentialValidator)
        if (!isValid) {
            stateHandle.update<LoginScreenState>(SCREEN_STATE) {
                it.copy(message = "Password is invalid")
            }
        }
        return isValid
    }

    private fun getErrorMessage(error: DataError): String {
        return when (error) {
            DataError.NetworkError.NotFound -> "User not found"
        }
    }
}
