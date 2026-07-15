package com.example.medcare.test

import com.example.medcare.models.Medicine
import com.example.medcare.unit.SearchMedicine
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SearchMedicineTest {

    private lateinit var searchMedicine: SearchMedicine

    @Before
    fun setup() {
        searchMedicine = SearchMedicine()
    }

    @Test
    fun searchMedicine_returnsMatchingMedicine() {

        val medicines = listOf(
            Medicine(
                1,
                "Paracetamol",
                20.0,
                0,
                "Pain relief medicine",
                "Fever and Pain",
                "1 Tablet",
                "Pain Relief",
                100
            ),
            Medicine(
                2,
                "Crocin",
                30.0,
                0,
                "Pain relief medicine",
                "Fever and Pain",
                "1 Tablet",
                "Pain Relief",
                100
            )
        )

        val result = searchMedicine.search(
            medicines,
            "Para"
        )

        assertEquals(1, result.size)
        assertEquals("Paracetamol", result[0].name)
    }
}
