package com.example.medcare.uii

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.medcare.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {

    LaunchedEffect(Unit) {

        delay(2000)

        navController.navigate("login") {

            popUpTo("splash") {
                inclusive = true
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.medcare_logo),
            contentDescription = "MedCare Logo",
            modifier = Modifier.size(180.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "MedCare",
            fontSize = 32.sp,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(30.dp))

        CircularProgressIndicator()
    }
}
