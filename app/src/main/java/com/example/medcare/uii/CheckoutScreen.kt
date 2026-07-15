package com.example.medcare.uii

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.medcare.manager.CartManager
import com.example.medcare.manager.OrderManager
import com.example.medcare.models.Order
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    navController: NavController
) {

    val context = LocalContext.current

    var address by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var paymentMethod by remember { mutableStateOf("Cash on Delivery") }

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text("Checkout")
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

    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            OutlinedTextField(
                value = address,
                onValueChange = {
                    address = it
                },
                label = {
                    Text("Delivery Address")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = phone,
                onValueChange = {
                    phone = it
                },
                label = {
                    Text("Phone Number")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Payment Method",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(10.dp))
            val paymentOptions = listOf(
                "Cash on Delivery",
                "UPI",
                "Credit/Debit Card"
            )

            paymentOptions.forEach { option ->

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {

                    RadioButton(
                        selected = paymentMethod == option,
                        onClick = {
                            paymentMethod = option
                        }
                    )

                    Text(
                        text = option,
                        modifier = Modifier.padding(top = 12.dp)
                    )

                }

            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Total Amount: ₹${CartManager.getTotalPrice()}",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {

                    if(CartManager.cartItems.isEmpty()) return@Button

                    val order = Order(
                        id = System.currentTimeMillis().toInt(),
                        items = CartManager.cartItems.toList(),
                        totalAmount = CartManager.getTotalPrice(),
                        address = address,
                        phone = phone,
                        paymentMethod = paymentMethod,
                        orderDate = java.text.SimpleDateFormat(
                            "dd MMM yyyy, hh:mm a",
                            java.util.Locale.getDefault()
                        ).format(java.util.Date())
                    )

                    OrderManager.addOrder(order)

                    CartManager.clearCart()

                    Toast.makeText(
                        context,
                        "Order placed successfully!",
                        Toast.LENGTH_LONG
                    ).show()

                    navController.navigate("order_history") {
                        popUpTo("cart") {
                            inclusive = true
                        }
                    }


                },
                modifier = Modifier.fillMaxWidth()
            ) {

                Text("Place Order")

            }
        }

    }

}

