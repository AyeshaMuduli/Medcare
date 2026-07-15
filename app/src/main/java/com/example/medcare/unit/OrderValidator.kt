package com.example.medcare.unit

import com.example.medcare.models.CartItem

class OrderValidator {

    fun isOrderValid(cartItems: List<CartItem>): Boolean {

        if (cartItems.isEmpty()) {
            return false
        }

        return cartItems.all {
            it.quantity > 0 &&
                    it.quantity <= it.medicine.stock
        }
    }
}