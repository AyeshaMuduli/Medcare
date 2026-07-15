package com.example.medcare.models

data class Order(

    val id: Int,
    val items: List<CartItem>,
    val totalAmount: Double,
    val address: String,
    val phone: String,
    val paymentMethod: String,
    val orderDate: String
)
