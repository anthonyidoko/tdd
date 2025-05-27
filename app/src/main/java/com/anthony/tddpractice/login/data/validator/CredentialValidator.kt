package com.anthony.tddpractice.login.data.validator

import com.anthony.tddpractice.login.domain.validator.ICredentialValidator

class CredentialValidator : ICredentialValidator {
    override fun validateUsername(username: String): Boolean {
        return true
    }

    override fun validatePassword(password: String): Boolean {
        TODO("Not yet implemented")
    }

}
