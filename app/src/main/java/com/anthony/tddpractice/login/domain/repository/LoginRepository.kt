package com.anthony.tddpractice.login.domain.repository

import com.anthony.tddpractice.login.domain.model.LoginCredential

interface LoginRepository {
    fun performLogin(loginCredentials: LoginCredential)

}
