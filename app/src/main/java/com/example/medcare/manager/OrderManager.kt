package com.example.medcare.manager

import androidx.compose.runtime.mutableStateListOf
import com.example.medcare.models.CartItem
import com.example.medcare.models.Order

object OrderManager {

    private val _orders = mutableStateListOf<Order>()

    val orders: List<Order>
        get() = _orders

    fun addOrder(order: Order) {
        _orders.add(0, order)
    }

    fun clearOrders() {
        _orders.clear()
    }
}
