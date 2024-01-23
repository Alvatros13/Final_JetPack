package com.example.prueba.ui.theme.vistas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.prueba.R
import com.example.prueba.ui.theme.model.City
import com.example.prueba.ui.theme.model.City.Companion.generarCiudades
import com.example.prueba.ui.theme.model.GetTimeAsyncTask

var citySelec = ""

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorldClockApp(navigationController: NavHostController) {
    var searchText by remember { mutableStateOf("") }
    var selectedCity by remember { mutableStateOf<City?>(null) }
    var cities = generarCiudades();
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Search City") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    // Handle search or filtering logic
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(cities.filter { it.name().contains(searchText, ignoreCase = true) }) { city ->
                CityItem(city = city, onClick = { selectedCity = city })
            }
        }

        selectedCity?.let {
            WorldClockCard(city = it, navigationController)
        }
    }
}

var currentTime ="Loading..."

@Composable
fun CityItem(city: City, onClick: (String) -> Unit) {

    Surface(
        modifier = Modifier.clickable {
            // Ejecutar GetTimeAsyncTask y obtener la hora actual
            val getTimeAsyncTask = GetTimeAsyncTask(city.timezone(), object : GetTimeAsyncTask.OnTimeFetchedListener {
                override fun onTimeFetched(result: String) {
                    // Almacena la hora actual
                    currentTime = result
                    onClick(result)
                }
            })
            getTimeAsyncTask.execute()
        },
        color = MaterialTheme.colorScheme.background
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = city.name(), style = MaterialTheme.typography.bodySmall)
                Text(text = city.timezone(), style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.weight(1f))
            //Text(text = currentTime, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun WorldClockCard(city: City, navigationController: NavHostController) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Column con weight para dejar espacio
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = city.name(),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(text = city.timezone(), color = Color.Gray)
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // IconButton al extremo derecho del Row
                IconButton(
                    onClick = {
                        navigationController.navigate("pantalla02")
                        citySelec = city.name()
                    },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(
                        painter  = painterResource(id = R.drawable.black_add),
                        contentDescription = null
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Cambiado el icono a uno personalizado desde drawable
                ClockItem(
                    icon = painterResource(id = R.drawable.black_access_time),
                    text = "Current Time",
                    value = currentTime
                )
                /*ClockItem(
                    icon = Icons.Default.Refresh,
                    text = "Last Updated",
                    value = "2 minutes ago" // Opcional: puedes actualizar esto con la hora actualizada
                )*/
            }
        }
    }
}

@Composable
fun ClockItem(icon: Painter, text: String, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(painter = icon, contentDescription = null)
        Column {
            Text(text = text, color = Color.Gray)
            Text(text = value)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWorldClockApp() {
    //WorldClockApp()
}