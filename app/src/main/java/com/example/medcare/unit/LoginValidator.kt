package com.example.medcare.unit

import android.util.Patterns

class LoginValidator {

    fun validate(email: String, password: String): Boolean {

        if (email.isBlank()) return false
        if (password.isBlank()) return false

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false
        }

        if (password.length < 6) {
            return false
        }

        return true
    }
}