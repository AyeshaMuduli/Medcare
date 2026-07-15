package com.example.medcare.utils

import android.util.Patterns

object ValidationUtils {

    fun isValidName(name: String): Boolean {
        return name.trim().length >= 3
    }

    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }

    fun isValidPhone(phone: String): Boolean {
        return phone.length == 10 && phone.all { it.isDigit() }
    }
}