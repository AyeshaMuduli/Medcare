package com.example.medcare.test

import com.example.medcare.models.CartItem
import com.example.medcare.models.Medicine
import com.example.medcare.unit.OrderValidator
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class OrderValidatorTest {

    private lateinit var validator: OrderValidator

    @Before
    fun setup() {
        validator = OrderValidator()
    }

    @Test
    fun validOrder_returnsTrue() {

        val medicine = Medicine(
            1,
            "Paracetamol",
            20.0,
            0,
            "Pain relief medicine",
            "Fever and Pain",
            "1 Tablet",
            "Pain Relief",
            100
        )

        val cart = listOf(
            CartItem(medicine, 2)
        )

        assertTrue(
            validator.isOrderValid(cart)
        )
    }

    @Test
    fun emptyCart_returnsFalse() {

        val cart = emptyList<CartItem>()

        assertFalse(
            validator.isOrderValid(cart)
        )
    }
}
