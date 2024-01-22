@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.prueba

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prueba.ui.theme.PruebaTheme
import com.example.prueba.ui.theme.model.Routes
import com.example.prueba.ui.theme.vistas.cityInfoScreen
import com.example.prueba.ui.theme.vistas.WorldClockApp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PruebaTheme{
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.Pantalla01.route
                    ) {
                        composable("pantalla02") { cityInfoScreen(navigationController)}
                        composable(Routes.Pantalla01.route) { WorldClockApp(navigationController) }
                        composable(Routes.Pantalla02.route) { cityInfoScreen(navigationController) }
                    }

                }
            }
        }
    }
}


