package com.anthony.tddpractice.login.data.validator

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CredentialValidatorTest {
    private val validator = CredentialValidator()
    private val validUsername = "::unImportant::"
    private val invalidUsername = " "

    @Test
    fun testValidUsername() {
        val expected = validator.validateUsername(validUsername)

        assertTrue(expected)
    }

    @Test
    fun testEmptyUsername() {
        val expected = validator.validateUsername(invalidUsername)

        assertFalse(expected)
    }

    @Test
    fun testNotEmptyButInvalidUsername() {
        val username = "lll"

        val expected = validator.validateUsername(username)

        assertFalse(expected)
    }
}