package com.example.medcare.unit

import com.example.medcare.models.Medicine

class SearchMedicine {

    fun search(
        medicines: List<Medicine>,
        keyword: String
    ): List<Medicine> {

        return medicines.filter {

            it.name.contains(keyword, ignoreCase = true) ||
                    it.category.contains(keyword, ignoreCase = true)
        }
    }
}