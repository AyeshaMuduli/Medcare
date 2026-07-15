package com.example.medcare.uii

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.medcare.manager.CartManager
import com.example.medcare.repository.MedicineRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineDetailsScreen(
    navController: NavController,
    medicineId: Int
) {

    val medicine = remember {
        MedicineRepository.getMedicineById(medicineId)
    }

    if (medicine == null) {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Medicine Details") },
                    navigationIcon = {
                        IconButton(
                            onClick = { navController.popBackStack() }
                        ) {
                            Icon(Icons.Default.ArrowBack, null)
                        }
                    }
                )
            }
        ) { padding ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text("Medicine not found.")
            }

        }

        return
    }

    var quantity by remember {
        mutableStateOf(1)
    }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text("Medicine Details")
                },

                navigationIcon = {

                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {

                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )

                    }

                }

            )

        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Image(
                painter = painterResource(medicine.imageRes),
                contentDescription = medicine.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = medicine.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Category: ${medicine.category}",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "₹${medicine.price}",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Description",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Text(medicine.description)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Uses",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Text(medicine.uses)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Dosage",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Text(medicine.dosage)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Available Stock: ${medicine.stock}"
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Quantity",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {

                OutlinedButton(
                    onClick = {
                        if (quantity > 1) quantity--
                    }
                ) {
                    Text("-")
                }

                Text(
                    text = quantity.toString(),
                    style = MaterialTheme.typography.titleLarge
                )

                OutlinedButton(
                    onClick = {
                        if (quantity < medicine.stock) quantity++
                    }
                ) {
                    Text("+")
                }

            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {

                    repeat(quantity) {
                        CartManager.addToCart(medicine)
                    }

                    navController.navigate("cart")
                },
                modifier = Modifier.fillMaxWidth()
            ) {

                Text("Add to Cart")

            }

        }

    }

}
