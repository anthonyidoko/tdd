package com.anthony.tddpractice.login.domain.validator

interface ICredentialValidator {
    fun validateUsername(username: String): Boolean
    fun validatePassword(password: String): Boolean
}