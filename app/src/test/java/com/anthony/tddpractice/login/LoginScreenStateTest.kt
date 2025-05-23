package com.anthony.tddpractice.login

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LoginScreenStateTest {
    @Test
    fun testInitialLoginScreenState() {
        val viewModel = LoginViewModel()
        val actual = viewModel.state.value
        val expected = LoginScreenState()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun testUsernameIsUpdated() {
        val newValue = "::unImportant"
        val viewModel = LoginViewModel()
        val previousState = viewModel.state.value
        val inputState = viewModel.state.value.inputState

        viewModel.updateUsername(newValue)

        assertThat(viewModel.state.value).isEqualTo(
            previousState.copy(
                inputState = inputState.copy(username = newValue)
            )
        )
    }

    @Test
    fun testPasswordIsUpdated() {
        val newPassword = "::unImportant"
        val viewModel = LoginViewModel()

        viewModel.updatePassword(newPassword)

        assertThat(viewModel.state.value).isEqualTo(viewModel.state.value.copy(
            inputState = viewModel.state.value.inputState.copy(password = newPassword)
        ))
    }
}