package com.example.medcare.uii

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.medcare.manager.SessionManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController
) {

    val user = SessionManager.currentUser

    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text("Profile")
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
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Surface(
                modifier = Modifier.size(110.dp),
                shape = CircleShape,
                tonalElevation = 6.dp
            ) {

                Box(
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(60.dp)
                    )

                }

            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = user?.name ?: "User",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        "Name",
                        fontWeight = FontWeight.Bold
                    )

                    Text(user?.name ?: "-")

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        "Email",
                        fontWeight = FontWeight.Bold
                    )

                    Text(user?.email ?: "-")

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        "Phone",
                        fontWeight = FontWeight.Bold
                    )

                    Text(user?.phone ?: "-")

                }

            }

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("order_history")
                    }
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        Icons.Default.History,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = "Order History",
                        modifier = Modifier.weight(1f),
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(">")

                }

            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {

                    SessionManager.logout()

                    navController.navigate("login") {

                        popUpTo(0)

                    }

                },
                modifier = Modifier.fillMaxWidth()
            ) {

                Icon(
                    Icons.Default.Logout,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text("Logout")

            }

        }

    }

}