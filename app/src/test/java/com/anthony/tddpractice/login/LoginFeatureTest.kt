package com.anthony.tddpractice.login

import androidx.lifecycle.SavedStateHandle
import com.anthony.tddpractice.login.data.repository.LoginRepositoryImpl
import com.anthony.tddpractice.login.data.validator.CredentialValidator
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginFeatureTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp(){
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun testLogin() = runTest {
        val expected = listOf(
            LoginScreenState(isLoading = true),
            LoginScreenState(message = "Login successful")
        )
        val viewModel = LoginViewModel(
            SavedStateHandle(),
            LoginRepositoryImpl(),
            CredentialValidator()
        )
        val actual = mutableListOf<LoginScreenState>()

        viewModel.performLogin()

        actual.add(viewModel.state.value)
        delay(2000)
        actual.add(viewModel.state.value)

        assertThat(actual).isEqualTo(expected)
    }
}
