package com.anthony.tddpractice.login.data.repository

import com.anthony.tddpractice.login.domain.model.LoginCredential
import com.anthony.tddpractice.login.domain.model.User
import com.anthony.tddpractice.login.domain.repository.LoginRepository
import com.anthony.tddpractice.login.domain.result.DataError
import com.anthony.tddpractice.login.domain.result.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InMemoryLoginRepository(private val users: List<User>) : LoginRepository {
    var wasLoginCalled: Boolean = false

    override fun performLogin(loginCredentials: LoginCredential): Flow<Result<User, DataError.NetworkError>> = flow{
        wasLoginCalled = true
        val foundResult = users.find { it.username == loginCredentials.username }
        if (foundResult != null) {
            emit(Result.Success(foundResult))
        } else {
            emit(Result.Failure(DataError.NetworkError.NotFound))
        }
    }


}
