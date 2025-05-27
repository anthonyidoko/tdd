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
        val username = " lll "

        val expected = validator.validateUsername(username)

        assertFalse(expected)
    }
    
    @Test
    fun testValidPassword(){
        val validPassword = "::Password@12::"

        val expected = validator.validatePassword(validPassword)

        assertTrue(expected)
    }

    @Test
    fun testShortPassword(){
        val shortPassword = "Pass1$"

        val expected = validator.validatePassword(shortPassword)
        
        assertFalse(expected)
    }
    
    @Test
    fun testPasswordWithoutSpecialCharater(){
        val password = "Password"

        val expected = validator.validatePassword(password)

        assertFalse(expected)
    }
    
    @Test
    fun testInvalidPassword(){
        val password = "       $"
        val expected = validator.validatePassword(password)
        assertFalse(expected)
    }
    
    @Test
    fun testPasswordWithoutUppercase(){
        val password = "password"

        val expected = validator.validatePassword(password)

        assertFalse(expected)
    }
    
    @Test
    fun testPasswordWithoutWithoutLowercase(){
        val password = "PASSWORD!@12"

        val expected = validator.validatePassword(password)

        assertFalse(expected)
    }
}