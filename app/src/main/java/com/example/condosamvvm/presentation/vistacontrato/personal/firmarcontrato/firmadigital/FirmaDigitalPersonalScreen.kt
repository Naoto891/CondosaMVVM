package com.example.condosamvvm.presentation.vistacontrato.personal.firmarcontrato.firmadigital

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.condosamvvm.presentation.login.HeaderImage
import kotlinx.coroutines.launch
import se.warting.signaturepad.SignaturePadAdapter
import se.warting.signaturepad.SignaturePadView
import java.io.ByteArrayOutputStream
import java.time.LocalDate

@Composable
fun FirmaDigitalPersonalScreen(
    idContrato: Int, firmaDigitalPersonalViewModel: FirmaDigitalPersonalViewModel, navController: NavHostController
) {
    val currentDate = LocalDate.now()
    val coroutineScope = rememberCoroutineScope()
    var signaturePadAdapter: SignaturePadAdapter? = null
    var signatureByteArray: ByteArray?

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xff1c4c96))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            // Aquí coloca la imagen de Condosa
            HeaderImage(
                Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .size(height = 100.dp, width = 100.dp)
                    .padding(top = 25.dp)
            )

            Text(
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = Color.White,
                text = "Condosa",
                modifier = Modifier
                    .padding(20.dp)
                    .align(Alignment.CenterVertically)
            )
        }
        SignaturePadView(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
                .aspectRatio(1f / 1.5f),
            onReady = {
                signaturePadAdapter = it
            }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { signaturePadAdapter?.clear() },
                colors = ButtonDefaults.buttonColors(contentColor = Color(0xff9AB4FF)),
            ) {
                Text(text = "Limpiar Firma", color = Color.White)
            }
            Button(
                onClick = {
                    coroutineScope.launch{
                        // Captura la firma como un Bitmap (ejemplo)


                        val signatureBitmap: Bitmap? = signaturePadAdapter?.getSignatureBitmap()

                        // Convierte el Bitmap a un ByteArray
                        signatureByteArray = bitmapToByteArray(signatureBitmap)



                        firmaDigitalPersonalViewModel.FirmarContratoPersonal(idContrato, currentDate, signatureByteArray!!){
                            navController.popBackStack()
                            navController.popBackStack()
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(contentColor = Color.White),
            ) {
                Text(text = "Guardar Firma", color = Color.White)
            }
        }
    }
}

fun bitmapToByteArray(bitmap: Bitmap?): ByteArray {
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap?.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    return byteArrayOutputStream.toByteArray()
}
