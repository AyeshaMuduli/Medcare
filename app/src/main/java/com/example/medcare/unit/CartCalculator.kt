package com.example.medcare.unit

import com.example.medcare.models.CartItem

class CartCalculator {

    fun calculateTotal(cartItems: List<CartItem>): Double {

        var total = 0.0

        for (item in cartItems) {
            total += item.medicine.price * item.quantity
        }

        return total
    }
}