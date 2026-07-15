package com.example.medcare.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.medcare.uii.*

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {

        composable("splash") {
            SplashScreen(navController)
        }

        composable("login") {
            LoginScreen(navController)
        }

        composable("register") {
            RegisterScreen(navController)
        }

        composable("home") {
            HomeScreen(navController)
        }

        composable("search") {
            SearchScreen(navController)
        }

        composable(
            route = "details/{medicineId}",
            arguments = listOf(
                navArgument("medicineId") {
                    type = NavType.IntType
                }
            )
        ) {

            val medicineId =
                it.arguments?.getInt("medicineId") ?: 0

            MedicineDetailsScreen(
                navController,
                medicineId
            )

        }

        composable("cart") {
            CartScreen(navController)
        }

        composable("checkout") {
            CheckoutScreen(navController)
        }

        composable("order_history") {
            OrderHistoryScreen(navController)
        }

        composable("profile") {
            ProfileScreen(navController)
        }

    }

}