package com.example.prueba.ui.theme.vistas

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.prueba.ui.theme.PruebaTheme
import com.google.accompanist.pager.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun cityInfoScreen(navigationController: NavHostController, citySelec : String) {
    var listaImg by remember { mutableStateOf<List<String>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        listaImg = downloadImg()
        loading = false
    }

    if (loading) {
        // Mientras se cargan las imágenes, muestra un indicador de progreso.
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
        )
        return
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth(),
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
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
                ImageCarousel(listaImg)
        }
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun ImageCarousel(urlList: List<String>) {

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        urlList.count()
    }

    HorizontalPager(state = pagerState) { page ->
        AsyncImage(
            model = urlList[page],
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }
}


suspend fun downloadImg(): List<String> = suspendCoroutine { continuation ->
    val database = Firebase.database
    val ref = database.getReference("citys")
    val cityRef = ref.child("Beijing")
    var listaImg: List<String> = emptyList()

    cityRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val value1 = snapshot.child("0").getValue().toString()
            val value2 = snapshot.child("1").getValue().toString()
            val value3 = snapshot.child("2").getValue().toString()
            listaImg = listOf(value1, value2, value3)
            Log.w(ContentValues.TAG, "Values are $value1 $value2 $value3")

            // Cuando la descarga se completa, se llama a la continuación con la lista de imágenes.
            continuation.resume(listaImg)
        }

        override fun onCancelled(error: DatabaseError) {
            Log.w(ContentValues.TAG, "Failed to read value.", error.toException())
            // Si ocurre un error, también se puede manejar con continuation.resumeWithException(error.toException())
        }
    })
}

@Preview(showBackground = true)
@Composable
fun PreviewMyScreenContent() {
    PruebaTheme {
        //MyScreenContent()
    }
}
