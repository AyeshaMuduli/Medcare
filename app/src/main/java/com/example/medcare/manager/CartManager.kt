package com.example.medcare.manager

import androidx.compose.runtime.mutableStateListOf
import com.example.medcare.models.CartItem
import com.example.medcare.models.Medicine

object CartManager {

    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> = _cartItems

    fun addToCart(medicine: Medicine) {
        val existingItem = _cartItems.find { it.medicine.id == medicine.id }

        if (existingItem != null) {
            existingItem.quantity++
            refresh()
        } else {
            _cartItems.add(CartItem(medicine, 1))
        }
    }

    fun increaseQuantity(medicineId: Int) {
        _cartItems.find { it.medicine.id == medicineId }?.let {
            it.quantity++
            refresh()
        }
    }

    fun decreaseQuantity(medicineId: Int) {
        _cartItems.find { it.medicine.id == medicineId }?.let {
            if (it.quantity > 1) {
                it.quantity--
                refresh()
            } else {
                _cartItems.remove(it)
            }
        }
    }

    fun removeItem(medicineId: Int) {
        _cartItems.removeAll { it.medicine.id == medicineId }
    }

    fun getTotalPrice(): Double {
        return _cartItems.sumOf { it.totalPrice() }
    }

    fun getTotalItems(): Int {
        return _cartItems.sumOf { it.quantity }
    }

    fun clearCart() {
        _cartItems.clear()
    }

    private fun refresh() {
        val temp = _cartItems.toList()
        _cartItems.clear()
        _cartItems.addAll(temp)
    }
}
