package com.example.medcare.uii


import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.medcare.database.DatabaseHelper
import com.example.medcare.manager.SessionManager

@Composable
fun LoginScreen(
    navController: NavController
) {

    val context = LocalContext.current
    val databaseHelper = remember { DatabaseHelper(context) }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Welcome Back",
            fontSize = 30.sp,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Login to continue",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text("Email")
            },
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = null)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text("Password")
            },
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = null)
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        passwordVisible = !passwordVisible
                    }
                ) {
                    Icon(
                        imageVector = if (passwordVisible)
                            Icons.Default.Visibility
                        else
                            Icons.Default.VisibilityOff,
                        contentDescription = null
                    )
                }
            },
            visualTransformation =
                if (passwordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                if (email.isBlank() || password.isBlank()) {

                    Toast.makeText(
                        context,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {

                    val success = databaseHelper.loginUser(email, password)

                    if (success) {

                        val user = databaseHelper.getUser(email)

                        SessionManager.currentUser = user
                        SessionManager.loggedInEmail = email

                        Toast.makeText(
                            context,
                            "Login Successful",
                            Toast.LENGTH_SHORT
                        ).show()

                        navController.navigate("home") {
                            popUpTo("login") {
                                inclusive = true
                            }
                        }

                    } else {

                        Toast.makeText(
                            context,
                            "Invalid Email or Password",
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                }

            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Login")

        }

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(
            onClick = {
                navController.navigate("register")
            }
        ) {

            Text("Create New Account")

        }

    }

}