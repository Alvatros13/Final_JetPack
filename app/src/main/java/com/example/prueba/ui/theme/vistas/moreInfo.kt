package com.example.prueba.ui.theme.vistas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.prueba.ui.theme.PruebaTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun cityInfoScreen(navigationController: NavHostController, citySelec : String) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        // TopAppBar with IconButton and Text in the center
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth(),
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.LightGray),
            title = {
                Text(
                    text = citySelec,
                    style = MaterialTheme.typography.bodyLarge,
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        navigationController.navigateUp()
                    }
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            },
        )

        // Content of the screen
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Text(text = "Hello, World!")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyScreenContent() {
    PruebaTheme {
        //MyScreenContent()
    }
}
