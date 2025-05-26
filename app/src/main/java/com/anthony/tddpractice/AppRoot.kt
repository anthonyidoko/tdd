package com.anthony.tddpractice

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anthony.tddpractice.login.LoginViewModel

@Composable
fun AppRoot() {
    LoginScreen()
}

@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = state.username,
            onValueChange = viewModel::updateUsername
        )
    }
}
