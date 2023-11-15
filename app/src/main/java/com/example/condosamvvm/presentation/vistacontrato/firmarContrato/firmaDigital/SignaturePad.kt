package com.example.condosamvvm.presentation.vistacontrato.firmarContrato.firmaDigital

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import se.warting.signaturepad.SignaturePadAdapter
import se.warting.signaturepad.SignaturePadView

@Composable
fun SignaturePad(
    idContrato: Int
){
    var signaturePadAdapter: SignaturePadAdapter? = null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            //Aquí coloca la imagen de Condosa

            Text(
                modifier = Modifier
                    .padding(20.dp),
                fontSize = 35.sp,
                text = "Condosa",
            )
        }
        SignaturePadView(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .aspectRatio(1f / 1.5f),
            onReady = {
                signaturePadAdapter = it
            }
        )
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { signaturePadAdapter?.clear() }) {
                Text(text = "Limpiar Firma")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Guardar Firma")
            }
        }
    }
}

