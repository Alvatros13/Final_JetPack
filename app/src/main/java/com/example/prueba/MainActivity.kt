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
import com.example.prueba.ui.theme.vistas.MyScreenContent
import com.example.prueba.ui.theme.vistas.WorldClockApp


/*class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PruebaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PreviewWorldClockApp()
                }
            }
        }
    }
}*/
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
                        composable(Routes.Pantalla01.route) { WorldClockApp(navigationController) }
                        composable(Routes.Pantalla02.route, ) { MyScreenContent(navigationController) }
                    }

                }
            }
        }
    }
}

/*class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavegacionTheme {
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
                        composable(Routes.Pantalla01.route) { Screen01(navigationController, activity = this@MainActivity) }
                        composable(Routes.Pantalla02.route) { Screen02(navigationController) }
                    }
                }
            }
        }
    }
}*/

/*
data class City(val name: String, val timezone: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorldClockApp() {
    var searchText by remember { mutableStateOf("") }
    var selectedCity by remember { mutableStateOf<City?>(null) }
    var cities by remember {
        mutableStateOf(
            listOf(
                City("New York", "America/New_York"),
                City("Los Angeles", "America/Los_Angeles"),
                City("Chicago", "America/Chicago"),
                City("Houston", "America/Chicago"),
                City("London", "Europe/London"),
                City("Paris", "Europe/Paris"),
                City("Berlin", "Europe/Berlin"),
                City("Tokyo", "Asia/Tokyo"),
                City("Beijing", "Asia/Shanghai"),
                City("Sydney", "Australia/Sydney"),
                City("Moscow", "Europe/Moscow"),
                City("Istanbul", "Europe/Istanbul"),
                City("Rio de Janeiro", "America/Sao_Paulo"),
                City("Cairo", "Africa/Cairo"),
                City("Mumbai", "Asia/Kolkata"),
                City("Cape Town", "Africa/Johannesburg"),
                City("Toronto", "America/Toronto"),
                City("Dubai", "Asia/Dubai"),
                City("Mexico City", "America/Mexico_City"),
                City("Seoul", "Asia/Seoul")
                // Add more cities as needed
            )
        )
    }

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
            items(cities.filter { it.name.contains(searchText, ignoreCase = true) }) { city ->
                CityItem(city = city, onClick = { selectedCity = city })
            }
        }

        selectedCity?.let {
            WorldClockCard(city = it)
        }
    }
}

var currentTime ="Loading..."

@Composable
fun CityItem(city: City, onClick: (String) -> Unit) {

    Surface(
        modifier = Modifier.clickable {
            // Ejecutar GetTimeAsyncTask y obtener la hora actual
            val getTimeAsyncTask = GetTimeAsyncTask(city.timezone, object : GetTimeAsyncTask.OnTimeFetchedListener {
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
                Text(text = city.name, style = MaterialTheme.typography.bodySmall)
                Text(text = city.timezone, style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.weight(1f))
            //Text(text = currentTime, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun WorldClockCard(city: City) {

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
                        text = city.name,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(text = city.timezone, color = Color.Gray)
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // IconButton al extremo derecho del Row
                IconButton(
                    onClick = {
                        // Lógica para manejar el clic del botón
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
                */
/*ClockItem(
                    icon = Icons.Default.Refresh,
                    text = "Last Updated",
                    value = "2 minutes ago" // Opcional: puedes actualizar esto con la hora actualizada
                )*//*

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
        WorldClockApp()
}
*/

