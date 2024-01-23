package com.example.prueba.ui.theme.vistas

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.prueba.R
import com.example.prueba.ui.theme.PruebaTheme
import com.google.accompanist.pager.*



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
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            ImageCarousel(
                imageList = listOf(
                        R.drawable.paris,
                        R.drawable.paris,
                        R.drawable.paris
                    ))
        }
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun ImageCarousel(imageList: List<Int>) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        imageList.count()
    }

    HorizontalPager(state = pagerState) { page ->
        Image(
            painter = painterResource(id = imageList[page]),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyScreenContent() {
    PruebaTheme {
        //MyScreenContent()
    }
}
