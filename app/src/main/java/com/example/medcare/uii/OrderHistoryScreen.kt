package com.example.medcare.uii

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.medcare.manager.OrderManager
import com.example.medcare.models.Order

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderHistoryScreen(
    navController: NavController
) {

    val orders = OrderManager.orders

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text("Order History")
                },

                navigationIcon = {

                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {

                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )

                    }

                }

            )

        }

    ) { paddingValues ->

        if (orders.isEmpty()) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {

                Text("No Orders Yet")

            }

        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(orders) { order ->

                    OrderCard(order)

                }

            }

        }

    }

}

@Composable
fun OrderCard(
    order: Order
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = "Order #${order.id}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Date: ${order.orderDate}")

            Spacer(modifier = Modifier.height(6.dp))

            Text("Address: ${order.address}")

            Spacer(modifier = Modifier.height(6.dp))

            Text("Phone: ${order.phone}")

            Spacer(modifier = Modifier.height(6.dp))

            Text("Payment: ${order.paymentMethod}")

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Medicines",
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            order.items.forEach { item ->

                Text(
                    text = "${item.medicine.name}  x${item.quantity}"
                )

            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Total: ₹${order.totalAmount}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

        }

    }

}
