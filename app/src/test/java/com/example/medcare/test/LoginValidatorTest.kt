package com.example.medcare.test

import com.example.medcare.unit.LoginValidator
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LoginValidatorTest {

    private lateinit var validator: LoginValidator

    @Before
    fun setup() {
        validator = LoginValidator()
    }

    @Test
    fun validLogin_returnsTrue() {
        assertTrue(
            validator.validate(
                "user@gmail.com",
                "password123"
            )
        )
    }

    @Test
    fun invalidEmail_returnsFalse() {
        assertFalse(
            validator.validate(
                "usergmail.com",
                "password123"
            )
        )
    }

    @Test
    fun shortPassword_returnsFalse() {
        assertFalse(
            validator.validate(
                "user@gmail.com",
                "123"
            )
        )
    }

    @Test
    fun emptyFields_returnsFalse() {
        assertFalse(
            validator.validate(
                "",
                ""
            )
        )
    }
}