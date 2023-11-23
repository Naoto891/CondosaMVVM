package com.example.condosamvvm.presentation.vistacontrato.personal.firmarcontrato

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.condosamvvm.presentation.Screen
import com.example.condosamvvm.presentation.login.HeaderImage

@Composable
fun FirmarContratoPersonalScreen(idContrato: Int, navController: NavHostController){

    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xff1c4c96))
    ){
        HeaderImage(
            Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .size(height = 100.dp, width = 100.dp)
                .padding(top = 25.dp)
        )

        Text(
            text = "Condosa",
            Modifier.align(Alignment.CenterHorizontally),
            fontSize = 21.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
        Box (
            Modifier
                .padding(10.dp)
                .background(Color(0xff9AB4FF))
        ){
            Text(
                text = "Contrato de Prestación de servicios",
                fontSize = 40.sp,
                lineHeight = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Box(
            Modifier
                .padding(10.dp)
                .background(Color.White)
        ){
            Text(text = "Bienvenido: $idContrato")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ){
            Button(
                onClick = { navController.navigate(Screen.FirmaDigitalPersonal.whitArgs(idContrato)) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff9AB4FF))
            ) {
                Text(text = "Subir firma", color = Color.Black)
            }
            Spacer(Modifier.padding(horizontal = 10.dp))
            Button(onClick = {  },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff9AB4FF))
            ) {
                Text(text = "Subir huella", color = Color.Black)
            }
        }


    }
}