package com.anthony.tddpractice.login.data.repository

import com.anthony.tddpractice.login.domain.model.LoginCredential
import com.anthony.tddpractice.login.domain.repository.LoginRepository

class InMemoryLoginRepository : LoginRepository {
    var wasLoginCalled: Boolean = false

    override fun performLogin(loginCredentials: LoginCredential) {
        wasLoginCalled = true
    }

}
