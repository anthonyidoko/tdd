package com.anthony.tddpractice.login.data.validator

import com.anthony.tddpractice.login.domain.validator.ICredentialValidator

class CredentialValidator : ICredentialValidator {
    override fun validateUsername(username: String): Boolean {
        val trimmed = username.trim()
        return trimmed.isNotBlank() &&
                trimmed.isValidLength(4)
    }

    override fun validatePassword(password: String): Boolean {
        return password.isValidLength(7) &&
                password.any { it.isDigit() } &&
                password.any { it.isLowerCase() } &&
                password.any { it.isUpperCase() } &&
                password.containsSpecialCharacter()
    }

    private fun String.isValidLength(min: Int): Boolean {
        return this.length >= min
    }

    private fun String.containsSpecialCharacter(): Boolean {
        val specialCharacters = "!@#$%^&*()_-+=<>,./?':\"|\\{}[];"
        return this.any { specialCharacters.contains(it) }
    }

}
