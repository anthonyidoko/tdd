package com.anthony.tddpractice.login.presentation

import androidx.lifecycle.SavedStateHandle
import com.anthony.tddpractice.login.LoginViewModel
import com.anthony.tddpractice.login.data.repository.InMemoryLoginRepository
import com.anthony.tddpractice.login.data.validator.CredentialValidator
import com.anthony.tddpractice.login.domain.model.LoginCredential
import com.anthony.tddpractice.login.domain.validator.ICredentialValidator
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue

class LoginViewModelTest {

    private val validCredentials =
        LoginCredential(username = "::username::", password = "::Password@1::")
    private val inValidCredentials = LoginCredential(username = " ", password = "Password")

    @Test
    fun testRepositoryPerformLoginWasCalled() {
        val repository = InMemoryLoginRepository()
        val viewModel = LoginViewModel(SavedStateHandle(), repository, mockValidator)

        viewModel.updateUsername(validCredentials.username)
        viewModel.updatePassword(validCredentials.password)
        viewModel.performLogin()

        assertTrue(repository.wasLoginCalled)
    }

    @Test
    fun testLoginNotCalledWithInvalidUsername() {
        val repository = InMemoryLoginRepository()
        val viewModel = LoginViewModel(SavedStateHandle(), repository, mockValidator2)

        viewModel.updateUsername(inValidCredentials.username)
        viewModel.performLogin()

        assertFalse(repository.wasLoginCalled)
    }

    @Test
    fun testLoginWithInvalidPassword() {
        val loginRepository = InMemoryLoginRepository()
        val viewModel = LoginViewModel(SavedStateHandle(), loginRepository, mockValidator2)

        viewModel.updatePassword(inValidCredentials.password)
        viewModel.performLogin()

        assertFalse(loginRepository.wasLoginCalled)
    }


    private val mockValidator = object : ICredentialValidator {
        override fun validateUsername(username: String): Boolean {
            return true
        }

        override fun validatePassword(password: String): Boolean {
            return true
        }

    }

    private val mockValidator2 = object : ICredentialValidator {
        override fun validateUsername(username: String): Boolean {
            return false
        }

        override fun validatePassword(password: String): Boolean {
            return false
        }

    }


}