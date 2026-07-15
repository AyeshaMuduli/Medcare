package com.example.medcare.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue

class CartItem(
    val medicine: Medicine,
    var quantity: Int = 1
){
    fun totalPrice(): Double{
        return medicine.price*quantity
    }
}
