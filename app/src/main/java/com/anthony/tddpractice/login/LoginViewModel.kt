package com.anthony.tddpractice.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val LOGIN_INPUT_STATE = "LOGIN_UI_STATE"

class LoginViewModel : ViewModel() {

    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()

    fun updateUsername(newValue: String) {
        val newInputState = _state.value.inputState.copy(username = newValue)
        _state.update {
            it.copy(inputState = newInputState)
        }
    }

    fun updatePassword(newValue: String) {
        val newInput = _state.value.inputState.copy(password = newValue)
        _state.update {
            it.copy(inputState = newInput)
        }
    }


}
