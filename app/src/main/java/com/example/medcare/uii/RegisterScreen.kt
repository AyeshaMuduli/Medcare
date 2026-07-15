package com.example.medcare.uii

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
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
import androidx.navigation.NavController
import com.example.medcare.database.DatabaseHelper
import com.example.medcare.models.User
import com.example.medcare.utils.ValidationUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController
) {

    val context = LocalContext.current
    val databaseHelper = remember { DatabaseHelper(context) }

    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Account") }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(20.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Full Name") },
                leadingIcon = { Icon(Icons.Default.Person, null) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone Number") },
                leadingIcon = { Icon(Icons.Default.Phone, null) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Default.Email, null) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                leadingIcon = { Icon(Icons.Default.Lock, null) },
                trailingIcon = {
                    IconButton(onClick = {
                        passwordVisible = !passwordVisible
                    }) {
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {

                    if (!ValidationUtils.isValidName(name)) {
                        Toast.makeText(context, "Enter a valid name", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    if (!ValidationUtils.isValidPhone(phone)) {
                        Toast.makeText(context, "Enter a valid phone number", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    if (!ValidationUtils.isValidEmail(email)) {
                        Toast.makeText(context, "Enter a valid email", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    if (!ValidationUtils.isValidPassword(password)) {
                        Toast.makeText(context, "Password should be at least 6 characters", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    val success = databaseHelper.registerUser(
                        User(
                            name = name,
                            email = email,
                            password = password,
                            phone = phone
                        )
                    )

                    if (success) {

                        Toast.makeText(
                            context,
                            "Registration Successful",
                            Toast.LENGTH_SHORT
                        ).show()

                        navController.popBackStack()

                    } else {

                        Toast.makeText(
                            context,
                            "Email already registered",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Register")
            }

        }
    }
}