package com.anthony.tddpractice.login

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginScreenState(
    val isLoading: Boolean = false,
    val isLoginError: Boolean = false,
    val message: String = "",
    val username: String = "",
    val password: String = ""
): Parcelable
