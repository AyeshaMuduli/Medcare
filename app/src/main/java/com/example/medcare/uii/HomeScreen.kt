package com.example.medcare.uii

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.medcare.manager.CartManager
import com.example.medcare.repository.MedicineRepository
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.medcare.utils.Constants
import androidx.compose.foundation.Image
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import com.example.medcare.models.Medicine

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
) {

//    val medicines = remember {
        var selectedCategory by remember {
            mutableStateOf("All")
        }

        val allMedicines = remember {
            MedicineRepository.getAllMedicines()
        }

        val medicines = remember(selectedCategory) {

            if (selectedCategory == "All") {

                allMedicines

            } else {

                allMedicines.filter {

                    it.category.equals(
                        selectedCategory,
                        ignoreCase = true
                    )

                }

            }

    }

    Scaffold(

        topBar = {

            CenterAlignedTopAppBar(
                title = {
                    Text("MedCare")
                }
            )
        },

        bottomBar = {

            NavigationBar {

                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "Home"
                        )
                    },
                    label = {
                        Text("Home")
                    }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate("search")
                    },
                    icon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    },
                    label = {
                        Text("Search")
                    }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate("cart")
                    },
                    icon = {

                        BadgedBox(
                            badge = {

                                if (CartManager.getTotalItems() > 0) {

                                    Badge {
                                        Text(
                                            CartManager
                                                .getTotalItems()
                                                .toString()
                                        )
                                    }
                                }
                            }
                        ) {

                            Icon(
                                Icons.Default.ShoppingCart,
                                contentDescription = "Cart"
                            )
                        }

                    },
                    label = {
                        Text("Cart")
                    }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate("profile")
                    },
                    icon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Profile"
                        )
                    },
                    label = {
                        Text("Profile")
                    }
                )

            }

        }

    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Search Bar
            item {

                HomeSearchBar(
                    navController = navController
                )

            }

            // Categories
            item {

                CategorySection(
                    selectedCategory = selectedCategory,
                    onCategorySelected = {
                        selectedCategory = it
                    }
                )

            }

            // Medicines

            items(medicines.size) { index ->

                MedicineCard(
                    medicine = medicines[index],
                    navController = navController
                )

            }

        }

    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSearchBar(
    navController: NavController
) {

    OutlinedTextField(
        value = "",
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("search")
            },
        enabled = false,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        placeholder = {
            Text("Search medicines...")
        },
        shape = RoundedCornerShape(16.dp)
    )
}

@Composable
fun CategorySection(
    selectedCategory: String,
    onCategorySelected: (String) -> Unit
) {

    val categories = listOf(
        "All",
        "Pain Relief",
        "Fever",
        "Heart",
        "Diabetes",
        "Antibiotic",
        "Vitamin",
        "Allergy",
        "Digestive"
    )

    Column {

        Text(
            text = "Categories",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.horizontalScroll(
                rememberScrollState()
            ),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            categories.forEach { category ->

                FilterChip(

                    selected = selectedCategory == category,

                    onClick = {
                        onCategorySelected(category)
                    },

                    label = {
                        Text(category)
                    }

                )

            }

        }

    }
}

@Composable
fun MedicineCard(
    medicine: Medicine,
    navController: NavController
) {

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        onClick = {
            navController.navigate("details/${medicine.id}")
        }
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Image(
                painter = painterResource(id = medicine.imageRes),
                contentDescription = medicine.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = medicine.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = medicine.category,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "₹${medicine.price}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Stock: ${medicine.stock}",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    CartManager.addToCart(medicine)
                }
            ) {
                Text("Add to Cart")
            }

        }

    }

}