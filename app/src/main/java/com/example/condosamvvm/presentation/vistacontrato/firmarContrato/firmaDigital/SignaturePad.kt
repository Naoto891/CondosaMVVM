package com.example.condosamvvm.presentation.vistacontrato.firmarContrato.firmaDigital

import android.graphics.Bitmap
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.condosamvvm.presentation.Screen
import com.example.condosamvvm.presentation.vistacontrato.ContratoViewModel
import kotlinx.coroutines.launch
import se.warting.signaturepad.SignaturePadAdapter
import se.warting.signaturepad.SignaturePadView
import java.io.ByteArrayOutputStream
import java.time.LocalDate

@Composable
fun SignaturePad(
    idContrato: Int, signaturePadViewModel: SignaturePadViewModel, navController: NavHostController
){
    val currentDate = LocalDate.now()
    val coroutineScope = rememberCoroutineScope()
    var signaturePadAdapter: SignaturePadAdapter? = null
    var signatureByteArray: ByteArray? = null



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
            Button(onClick = {

                coroutineScope.launch{
                    // Captura la firma como un Bitmap (ejemplo)
                    val signatureBitmap: Bitmap? = signaturePadAdapter?.getSignatureBitmap()

                    // Convierte el Bitmap a un ByteArray
                    signatureByteArray = bitmapToByteArray(signatureBitmap)



                    signaturePadViewModel.FirmarContratoEmpleado(idContrato, currentDate, signatureByteArray!!){

                        navController.navigate(Screen.Contrato.route){
                            popUpTo(Screen.Empleado.route)
                        }
                    }
                }

            }) {
                Text(text = "Guardar Firma")
            }
        }
    }
}

fun bitmapToByteArray(bitmap: Bitmap?): ByteArray {
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap?.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    return byteArrayOutputStream.toByteArray()
}