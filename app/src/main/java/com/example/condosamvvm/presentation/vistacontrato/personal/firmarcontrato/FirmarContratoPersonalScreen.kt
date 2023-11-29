package com.example.condosamvvm.presentation.vistacontrato.personal.firmarcontrato

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.condosamvvm.domain.model.ContratoData
import com.example.condosamvvm.presentation.Screen
import com.example.condosamvvm.presentation.login.HeaderImage

@Composable
fun FirmarContratoPersonalScreen(idContrato: Int,firmarContratoPersonalViewModel: FirmarContratoPersonalViewModel, navController: NavHostController){

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
        Box(
            Modifier
                .padding(10.dp)
                .background(Color.White)
        ){
            Contrato( firmarContratoPersonalViewModel,idContrato)
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

@Composable
fun Contrato (firmarContratoPersonalViewModel: FirmarContratoPersonalViewModel, idContrato: Int){
    val contratoData: ContratoData ? by firmarContratoPersonalViewModel.contratoData.observeAsState()
    firmarContratoPersonalViewModel.getContratoData(idContrato)

    System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray.copy(alpha = 0.8f))
            .verticalScroll(rememberScrollState()) // Hace el contenido scrollable
            .height(100.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier,
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "CONTRATO DE PRESTACION DE\nSERVICIOS - CONDOSA",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier) {
                    Text(
                        text = "Entre la empresa:"
                    )
                }
                Box(modifier = Modifier.padding(start = 16.dp).border(width = 1.dp, color = Color.Black)) {
                    contratoData?.let {
                        Text(
                            text = "empresa",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier) {
                    Text(
                        text = "Representada en este acto por:"
                    )
                }
                Box(modifier = Modifier.padding(start = 16.dp).border(width = 1.dp, color = Color.Black)) {
                    Text(
                        text = "SOLICITANTE NOMBRES",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier) {
                    Text(
                        text = "Identificado con :"
                    )
                }
                Box(modifier = Modifier.padding(start = 16.dp).border(width = 1.dp, color = Color.Black)) {
                    Text(
                        text = "DNI",
                        fontWeight = FontWeight.Bold
                    )
                }
                Box(modifier = Modifier.padding(start = 16.dp).border(width = 1.dp, color = Color.Black)) {
                    Text(
                        text = "NUMERODNI",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier) {
                    Text(
                        text = "En su caracter de :"
                    )
                }
                Box(modifier = Modifier.padding(start = 16.dp).border(width = 1.dp, color = Color.Black)) {
                    Text(
                        text = "ROL SOLICITANTE",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier) {
                    Text(
                        text = "De :"
                    )
                }
                Box(modifier = Modifier.padding(start = 16.dp).border(width = 1.dp, color = Color.Black)) {
                    Text(
                        text = "PREDIO DESCRIP",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier) {
                    Text(
                        text = "En lo sucesivo :"
                    )
                }
                Box(modifier = Modifier.padding(start = 16.dp).border(width = 1.dp, color = Color.Black)) {
                    Text(
                        text = "LA EMPRESA CONTRATANTE",
                        fontWeight = FontWeight.Bold
                    )
                }
            }





            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier) {
                    Text(
                        text = "Y la empresa:"
                    )
                }
                Box(modifier = Modifier.padding(start = 16.dp).border(width = 1.dp, color = Color.Black)) {
                    Text(
                        text = "CONDOSA",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier) {
                    Text(
                        text = "Representada en este acto por:"
                    )
                }
                Box(modifier = Modifier.padding(start = 16.dp).border(width = 1.dp, color = Color.Black)) {
                    Text(
                        text = "SOLICITANTE NOMBRES",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier) {
                    Text(
                        text = "Identificado con :"
                    )
                }
                Box(modifier = Modifier.padding(start = 16.dp).border(width = 1.dp, color = Color.Black)) {
                    Text(
                        text = "DNI",
                        fontWeight = FontWeight.Bold
                    )
                }
                Box(modifier = Modifier.padding(start = 16.dp).border(width = 1.dp, color = Color.Black)) {
                    Text(
                        text = "NUMERODNI",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier) {
                    Text(
                        text = "En su caracter de :"
                    )
                }
                Box(modifier = Modifier.padding(start = 16.dp).border(width = 1.dp, color = Color.Black)) {
                    Text(
                        text = "ROL SOLICITANTE",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier) {
                    Text(
                        text = "De :"
                    )
                }
                Box(modifier = Modifier.padding(start = 16.dp).border(width = 1.dp, color = Color.Black)) {
                    Text(
                        text = "CONDOSA",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier) {
                    Text(
                        text = "En lo sucesivo :"
                    )
                }
                Box(modifier = Modifier.padding(start = 16.dp).border(width = 1.dp, color = Color.Black)) {
                    Text(
                        text = "LA EMPRESA CONTRATISTA",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }


}