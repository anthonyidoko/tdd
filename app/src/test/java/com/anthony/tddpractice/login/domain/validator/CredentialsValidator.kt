package com.anthony.tddpractice.login.domain.validator

class CredentialsValidator {
    companion object {
        fun validateUsername(username: String): Boolean {
            return username.isNotBlank()
        }
    }

}
