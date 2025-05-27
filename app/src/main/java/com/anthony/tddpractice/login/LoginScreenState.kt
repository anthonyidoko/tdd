package com.anthony.tddpractice.login

import android.os.Parcelable
import com.anthony.tddpractice.login.domain.validator.ICredentialValidator
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginScreenState(
    val isLoading: Boolean = false,
    val isLoginError: Boolean = false,
    val message: String = "",
    val username: String = "",
    val password: String = ""
): Parcelable


fun LoginScreenState.isPasswordValid(validator: ICredentialValidator): Boolean {
    return validator.validatePassword(password)
}

fun LoginScreenState.isUsernameValid(validator: ICredentialValidator): Boolean {
    return validator.validateUsername(username)
}

