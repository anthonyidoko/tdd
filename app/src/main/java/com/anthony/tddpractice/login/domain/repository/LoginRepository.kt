package com.anthony.tddpractice.login.domain.repository

import com.anthony.tddpractice.login.domain.model.LoginCredential
import com.anthony.tddpractice.login.domain.model.User
import com.anthony.tddpractice.login.domain.result.DataError
import com.anthony.tddpractice.login.domain.result.Result
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun performLogin(loginCredentials: LoginCredential): Flow<Result<User, DataError.NetworkError>>

}
