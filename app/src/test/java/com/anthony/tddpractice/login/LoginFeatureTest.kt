package com.anthony.tddpractice.login

import androidx.lifecycle.SavedStateHandle
import com.anthony.tddpractice.login.data.repository.InMemoryLoginRepository
import com.anthony.tddpractice.login.data.repository.LoginRepositoryImpl
import com.anthony.tddpractice.login.data.validator.CredentialValidator
import com.anthony.tddpractice.login.domain.model.LoginCredential
import com.anthony.tddpractice.login.extensions.CoroutineTestExtension
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(CoroutineTestExtension::class)
class LoginFeatureTest {

    private val validCredentials =
        LoginCredential(username = bob.username, password = "ValidPassword@12")
    private lateinit var viewModel  :LoginViewModel
    @BeforeEach
    fun setUp(){
        viewModel = LoginViewModel(
            SavedStateHandle(),
            LoginRepositoryImpl(),
            CredentialValidator()
        )
    }

    @Test
    fun testLogin() = runTest {
        val expected = listOf(
            LoginScreenState(
                isLoading = false,
                username = validCredentials.username,
                password = validCredentials.password
            ),
            LoginScreenState(
                message = "Login successful",
                username = validCredentials.username,
                password = validCredentials.password
            )
        )


        viewModel.updatePassword(validCredentials.password)
        viewModel.updateUsername(validCredentials.username)
        val actual = collectStateflow(viewModel.state){
            viewModel.performLogin()
        }


        println(actual)
        println(expected)
        assertThat(actual).isEqualTo(expected)
    }

    private fun <T> CoroutineScope.collectStateflow(stateFlow: StateFlow<T>, block: () -> Unit): List<T> {
        val destination = mutableListOf<T>()
        val job = launch(Dispatchers.Unconfined) {
            stateFlow.collect { item ->
                destination.add(item)
            }
        }
        block()
        job.cancel()

        return destination
    }
}
