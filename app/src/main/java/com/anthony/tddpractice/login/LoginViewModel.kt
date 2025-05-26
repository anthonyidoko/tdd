package com.anthony.tddpractice.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

private const val SCREEN_STATE = "SCREEN_STATE"

class LoginViewModel(
    private val stateHandle: SavedStateHandle,
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
        TODO()
    }

}
