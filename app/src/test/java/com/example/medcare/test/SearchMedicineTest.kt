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
                "",
                "Tablet",
                20.0,
                100,
                0
            ),
            Medicine(
                2,
                "Crocin",
                "",
                "Tablet",
                30.0,
                100,
                0
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