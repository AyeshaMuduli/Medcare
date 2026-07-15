package com.example.medcare.repository

import com.example.medcare.models.Medicine
import com.example.medcare.R

object MedicineRepository {

    private val medicines = listOf<Medicine>(
        Medicine(
            id = 1,
            name = "Paracetamol 500mg",
            price = 25.0,
            category = "Fever",
            description = "Provides relief from fever and mild to moderate pain.",
            uses = "Fever, Headache, Body Pain",
            dosage = "1 tablet every 6 hours after food",
            imageRes = R.drawable.paracetamol,
            stock = 120
        ),

        Medicine(
            id = 2,
            name = "Crocin Advance",
            price = 35.0,
            category = "Fever",
            description = "Fast acting medicine for fever and pain relief.",
            uses = "Fever, Headache",
            dosage = "1 tablet after food",
            imageRes = R.drawable.crocin,
            stock = 90
        ),

        Medicine(
            id = 3,
            name = "Dolo 650",
            price = 30.0,
            category = "Fever",
            description = "Common medicine for fever and body pain.",
            uses = "Fever, Body Pain",
            dosage = "1 tablet every 6 hours",
            imageRes = R.drawable.dolo650,
            stock = 100
        ),

        Medicine(
            id = 4,
            name = "Cetirizine",
            price = 18.0,
            category = "Cold & Cough",
            description = "Relieves allergy symptoms such as sneezing and runny nose.",
            uses = "Allergy, Cold",
            dosage = "1 tablet at night",
            imageRes = R.drawable.cetirizine,
            stock = 80
        ),

        Medicine(
            id = 5,
            name = "Benadryl Syrup",
            price = 95.0,
            category = "Cold & Cough",
            description = "Effective cough syrup for dry and wet cough.",
            uses = "Cough, Throat Irritation",
            dosage = "10 ml twice daily",
            imageRes = R.drawable.benadryl,
            stock = 50
        ),

        Medicine(
            id = 6,
            name = "Vitamin C Tablets",
            price = 150.0,
            category = "Vitamin",
            description = "Supports immunity and overall health.",
            uses = "Vitamin C Deficiency",
            dosage = "1 tablet daily",
            imageRes = R.drawable.vitamin_c,
            stock = 75
        ),

        Medicine(
            id = 7,
            name = "Limcee 500",
            price = 90.0,
            category = "Vitamin",
            description = "Chewable Vitamin C tablets.",
            uses = "Immunity Booster",
            dosage = "1 tablet daily",
            imageRes = R.drawable.limcee,
            stock = 60
        ),


        Medicine(
            id = 9,
            name = "Digene",
            price = 110.0,
            category = "Digestive",
            description = "Antacid for acidity and indigestion.",
            uses = "Acidity, Heartburn",
            dosage = "2 teaspoons after meals",
            imageRes = R.drawable.digene,
            stock = 65
        ),

        Medicine(
            id = 10,
            name = "Ibuprofen 400mg",
            price = 40.0,
            category = "Pain Relief",
            description = "Reduces pain, inflammation and fever.",
            uses = "Pain, Inflammation",
            dosage = "1 tablet after meals",
            imageRes = R.drawable.ibuprofen,
            stock = 95
        ),

        Medicine(
            id = 11,
            name = "Amoxicillin 500mg",
            price = 120.0,
            category = "Antibiotic",
            description = "Antibiotic used to treat bacterial infections.",
            uses = "Bacterial infections",
            dosage = "1 capsule three times a day after meals",
            imageRes = R.drawable.amoxicillin,
            stock = 70
        ),

        Medicine(
            id = 12,
            name = "Azithromycin 500mg",
            price = 140.0,
            category = "Antibiotic",
            description = "Treats bacterial infections affecting the throat, lungs and skin.",
            uses = "Respiratory and skin infections",
            dosage = "1 tablet daily",
            imageRes = R.drawable.azithromycin,
            stock = 65
        ),

        Medicine(
            id = 13,
            name = "Metformin 500mg",
            price = 95.0,
            category = "Diabetes",
            description = "Helps control blood sugar levels in people with diabetes.",
            uses = "Type 2 Diabetes",
            dosage = "1 tablet after breakfast and dinner",
            imageRes = R.drawable.metformin,
            stock = 100
        ),

        Medicine(
            id = 14,
            name = "Glimepiride 2mg",
            price = 110.0,
            category = "Diabetes",
            description = "Medicine used to lower blood sugar levels.",
            uses = "Type 2 Diabetes",
            dosage = "1 tablet before breakfast",
            imageRes = R.drawable.glimepiride,
            stock = 90
        ),

        Medicine(
            id = 15,
            name = "Aspirin 75mg",
            price = 55.0,
            category = "Heart",
            description = "Helps prevent blood clots and supports heart health.",
            uses = "Heart protection",
            dosage = "1 tablet daily",
            imageRes = R.drawable.aspirin,
            stock = 120
        ),

        Medicine(
            id = 16,
            name = "Pantoprazole 40mg",
            price = 125.0,
            category = "Digestive",
            description = "Reduces stomach acid and treats acidity and ulcers.",
            uses = "Acidity, Gastric ulcer",
            dosage = "1 tablet before breakfast",
            imageRes = R.drawable.pantoprazole,
            stock = 85
        ),

        Medicine(
            id = 17,
            name = "Volini Gel",
            price = 150.0,
            category = "Pain Relief",
            description = "Pain relief gel for muscle and joint pain.",
            uses = "Muscle pain, Joint pain",
            dosage = "Apply 3-4 times daily",
            imageRes = R.drawable.volini,
            stock = 55
        ),

        Medicine(
            id = 18,
            name = "Moov Spray",
            price = 210.0,
            category = "Pain Relief",
            description = "Fast acting spray for muscle and back pain.",
            uses = "Back pain, Muscle pain",
            dosage = "Spray on affected area when needed",
            imageRes = R.drawable.moov,
            stock = 40
        ),

        Medicine(
            id = 19,
            name = "Boroline Cream",
            price = 45.0,
            category = "Skin Care",
            description = "Antiseptic cream for cuts, cracks and dry skin.",
            uses = "Cuts, Dry skin",
            dosage = "Apply twice daily",
            imageRes = R.drawable.boroline,
            stock = 75
        ),

        Medicine(
            id = 20,
            name = "Soframycin Cream",
            price = 95.0,
            category = "Skin Care",
            description = "Antibiotic cream for minor cuts, wounds and burns.",
            uses = "Cuts, Wounds, Burns",
            dosage = "Apply on affected area as directed",
            imageRes = R.drawable.soframycin,
            stock = 50
        ),

        Medicine(
            id = 21,
            name = "ORS Powder",
            price = 25.0,
            category = "Digestive",
            description = "Oral Rehydration Salts help prevent dehydration caused by diarrhea, vomiting, or excessive sweating.",
            uses = "Dehydration, Diarrhea",
            dosage = "Mix one sachet in 1 litre of clean water and drink as needed.",
            imageRes = R.drawable.ors,
            stock = 100
        ),

        Medicine(
            id = 22,
            name = "Zincovit Tablets",
            price = 180.0,
            category = "Vitamin",
            description = "Multivitamin and multimineral supplement for overall health and immunity.",
            uses = "Vitamin deficiency, Immunity",
            dosage = "1 tablet daily after meals",
            imageRes = R.drawable.zincovit,
            stock = 80
        ),

        Medicine(
            id = 23,
            name = "Vicks VapoRub",
            price = 165.0,
            category = "Cold & Cough",
            description = "Provides relief from cough, nasal congestion, and minor body aches.",
            uses = "Cold, Cough, Nasal Congestion",
            dosage = "Apply on chest, throat, and back as directed.",
            imageRes = R.drawable.vicks,
            stock = 60
        ),

        Medicine(
            id = 24,
            name = "Strepsils Lozenges",
            price = 40.0,
            category = "Cold & Cough",
            description = "Medicated lozenges that soothe sore throat and mouth infections.",
            uses = "Sore throat, Mouth infection",
            dosage = "Dissolve one lozenge slowly in the mouth every 2-3 hours.",
            imageRes = R.drawable.strepsils,
            stock = 70
        )
        // Medicines will be added here
    )

    fun getAllMedicines(): List<Medicine> = medicines

    fun getMedicineById(id: Int): Medicine? {
        return medicines.find { it.id == id }
    }

    fun searchMedicines(query: String): List<Medicine> {
        if (query.isBlank()) return medicines

        return medicines.filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.category.contains(query, ignoreCase = true)
        }
    }

    fun getMedicinesByCategory(category: String): List<Medicine> {
        if (category == "All") return medicines

        return medicines.filter {
            it.category.equals(category, ignoreCase = true)
        }
    }
}
