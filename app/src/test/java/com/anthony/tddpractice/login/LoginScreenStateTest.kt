package com.anthony.tddpractice.login

import androidx.lifecycle.SavedStateHandle
import com.anthony.tddpractice.login.data.repository.InMemoryLoginRepository
import com.anthony.tddpractice.login.data.validator.CredentialValidator
import com.anthony.tddpractice.login.domain.model.User
import com.anthony.tddpractice.login.domain.model.UserOccupation
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class LoginScreenStateTest {
    private val stateHandle = SavedStateHandle()
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp(){
        viewModel = LoginViewModel(stateHandle, InMemoryLoginRepository(listOf(bob, jake, ali)), CredentialValidator())
    }

    @Test
    fun testInitialLoginScreenState() {
        val actual = viewModel.state.value
        val expected = LoginScreenState()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun testUsernameIsUpdated() {
        val newValue = "::unImportant::"

        viewModel.updateUsername(newValue)

        assertThat(viewModel.state.value.username).isEqualTo(newValue)
    }

    @Test
    fun testPasswordIsUpdated() {
        val newPassword = "::unImportant::"
        viewModel.updatePassword(newPassword)

        assertThat(viewModel.state.value.password).isEqualTo(newPassword)
    }

    @Test
    fun testUpdateStateWithEmptyUsernameAndEmptyPassword() {
        viewModel.updateState()

        assertThat(viewModel.state.value).isEqualTo(LoginScreenState())
    }

    @Test
    fun testUpdateStateWithUserName(){
        val username = "::unimportant::"
        val viewModel = LoginViewModel(
            stateHandle,
            InMemoryLoginRepository(listOf(bob, jake, ali)),
            CredentialValidator()
        )

        viewModel.updateUsername(username)
        viewModel.updateState()

        assertThat(viewModel.state.value.username).isEqualTo(username)
    }
}