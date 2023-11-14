package com.example.condosamvvm.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.condosamvvm.R
import com.example.condosamvvm.presentation.Screen
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun HomeScreen(navController: NavController) {

    LaunchedEffect(key1 = true ){
        delay(1.seconds)
        navController.popBackStack()
        navController.navigate(Screen.Login.route)
    }

    Home()
}

@Composable
fun Home(){
    Column(
        Modifier.fillMaxSize().background(Color(0xff000035)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.condosa_1),
            contentDescription = "Header",
        )

    }
}
