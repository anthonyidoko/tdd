package com.anthony.tddpractice.login

import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LoginScreenStateTest {
    private val stateHandle = SavedStateHandle()

    @Test
    fun testInitialLoginScreenState() {
        val viewModel = LoginViewModel(SavedStateHandle())
        val actual = viewModel.state.value
        val expected = LoginScreenState()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun testUsernameIsUpdated() {
        val newValue = "::unImportant::"
        val viewModel = LoginViewModel(stateHandle)

        viewModel.updateUsername(newValue)

        assertThat(viewModel.state.value.username).isEqualTo(newValue)
    }

    @Test
    fun testPasswordIsUpdated() {
        val newPassword = "::unImportant::"
        val viewModel = LoginViewModel(stateHandle)

        viewModel.updatePassword(newPassword)

        assertThat(viewModel.state.value.password).isEqualTo(newPassword)
    }

    @Test
    fun testUpdateStateWithEmptyUsernameAndEmptyPassword() {
        val viewModel = LoginViewModel(stateHandle)

        viewModel.updateState()

        assertThat(viewModel.state.value).isEqualTo(LoginScreenState())
    }

    @Test
    fun testUpdateStateWithUserName(){
        val username = "::unimportant::"
        val viewModel = LoginViewModel(stateHandle)

        viewModel.updateUsername(username)
        viewModel.updateState()

        assertThat(viewModel.state.value.username).isEqualTo(username)
    }
}