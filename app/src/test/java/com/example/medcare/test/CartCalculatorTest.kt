package com.example.medcare.test

import com.example.medcare.models.CartItem
import com.example.medcare.models.Medicine
import com.example.medcare.unit.CartCalculator
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CartCalculatorTest {

    private lateinit var calculator: CartCalculator

    @Before
    fun setup() {
        calculator = CartCalculator()
    }

    @Test
    fun calculateTotal_returnsCorrectValue() {

        val medicine = Medicine(
            1,
            "Paracetamol",
            "Pain Relief",
            "Tablet",
            50.0,
            100,
            0
        )

        val cart = listOf(
            CartItem(medicine, 2)
        )

        assertEquals(
            100.0,
            calculator.calculateTotal(cart),
            0.01
        )
    }
}