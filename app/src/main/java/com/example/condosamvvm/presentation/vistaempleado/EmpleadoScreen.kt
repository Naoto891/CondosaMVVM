package com.example.condosamvvm.presentation.vistaempleado

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.condosamvvm.R
import com.example.condosamvvm.presentation.Screen
import kotlinx.coroutines.launch

@Composable
fun EmpleadoScreen(idPersona: Int,empleadoViewModel: EmpleadoViewModel, navController: NavHostController) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color(0xFF1c4c96))
    ) {
        Empleado(Modifier.align(Alignment.Center), empleadoViewModel, navController, idPersona)
    }
}

@Composable
fun Empleado(modifier: Modifier, empleadoViewModel: EmpleadoViewModel, navController: NavHostController, idPersona: Int) {


    val isLoading: Boolean by empleadoViewModel.isLoading.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()



    if(isLoading){
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }else{

        Column(modifier = modifier) {
            HeaderImage(
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(height = 200.dp, width = 200.dp)
            )
            Text(
                text = "CONDOSA",
                Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(bottom = 50.dp), fontSize = 32.sp, color = Color.White
            )
            Spacer(modifier = Modifier.padding(4.dp))
            ContratosButton {
                coroutineScope.launch {
                    navController.navigate(Screen.ContratoEmpleado.whitArgs(idPersona))
                }
            }
            Spacer(modifier = Modifier.padding(16.dp))
            CotizacionesButton{}
            Spacer(modifier = Modifier.padding(75.dp))
        }
    }
}

@Composable
fun ContratosButton( onContratoSelected: () -> Unit) {
    Button(
        onClick = {onContratoSelected()},
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(40.dp, 0.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "Contratos")
    }
}

@Composable
fun CotizacionesButton( onCotizacionSelected: () -> Unit) {
    Button(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(40.dp, 0.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text(text = "Cotizaciones")
    }
}


@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.condosa_1),
        contentDescription = "Header",
        modifier = modifier
    )
}