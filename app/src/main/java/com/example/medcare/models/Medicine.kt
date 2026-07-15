package com.example.medcare.models

data class Medicine(
    val id: Int,
    val name: String,
    val price: Double,
    val imageRes: Int,
    val description: String,
    val uses: String,
    val dosage: String,
    val category: String,
    val stock: Int
)
