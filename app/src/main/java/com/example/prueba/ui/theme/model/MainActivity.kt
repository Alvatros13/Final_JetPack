@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.prueba.ui.theme.model

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prueba.ui.theme.vistas.WorldClockApp
import com.example.prueba.ui.theme.vistas.cityInfoScreen
import com.example.prueba.ui.theme.vistas.citySelec


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    //uploadCiudades()
                    //downloadImg()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.Pantalla01.route
                    ) {
                        composable("pantalla02") { cityInfoScreen(navigationController, citySelec)}
                        composable(Routes.Pantalla01.route) { WorldClockApp(navigationController) }
                        composable(Routes.Pantalla02.route) { cityInfoScreen(navigationController, citySelec) }
                    }

                }
        }
    }
}


