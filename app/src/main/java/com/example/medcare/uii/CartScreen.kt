package com.example.medcare.uii

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.medcare.manager.CartManager
import com.example.medcare.models.CartItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    navController: NavController
) {

    val cartItems = CartManager.cartItems

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text("My Cart")
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

        if (cartItems.isEmpty()) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {

                Text("Your cart is empty.")

            }

        } else {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    items(cartItems) { cartItem ->

                        CartItemCard(
                            cartItem = cartItem
                        )

                    }

                }

                Divider()

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Total: ₹${CartManager.getTotalPrice()}",
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        navController.navigate("checkout")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text("Proceed to Checkout")

                }

            }

        }

    }

}

@Composable
fun CartItemCard(
    cartItem: CartItem
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Image(
                painter = painterResource(id = cartItem.medicine.imageRes),
                contentDescription = cartItem.medicine.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = cartItem.medicine.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "₹${cartItem.medicine.price}"
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                OutlinedButton(
                    onClick = {
                        CartManager.decreaseQuantity(cartItem.medicine.id)
                    }
                ) {
                    Text("-")
                }

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = cartItem.quantity.toString(),
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.width(12.dp))

                OutlinedButton(
                    onClick = {
                        CartManager.increaseQuantity(cartItem.medicine.id)
                    }
                ) {
                    Text("+")
                }

            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    CartManager.removeItem(cartItem.medicine.id)
                },
                modifier = Modifier.fillMaxWidth()
            ) {

                Text("Remove")

            }

        }

    }

}
