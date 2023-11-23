package com.example.condosamvvm.presentation.vistacontrato.solicitante.firmarcontrato.contratosfirmados

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import com.example.condosamvvm.R
import java.io.ByteArrayInputStream

@Composable
fun ContratosFirmadosSolicitantesScreen(idContrato: Int, idSolicitante: Int, contratosFirmadosSolicitantesViewModel: ContratosFirmadosSolicitantesViewModel) {
    // Cargar la firma personal cuando se inicie la vista
    val imagenFirma: ByteArray? by contratosFirmadosSolicitantesViewModel.imagenFirma.observeAsState(null)

    contratosFirmadosSolicitantesViewModel.getImagenFirma(idContrato, idSolicitante)

    // Verificar si la imagenFirma no es nula antes de llamar a byteArrayToBitmap
    val imageBitmap = imagenFirma?.let { convertImageByteArrayToBitmap(it) }

    // Mostrar la imagen utilizando el composable ImageFromByteArray
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorResource(id = R.color.white)
    ) {
        if (imageBitmap != null) {
            BitmapImage(imageBitmap)
        }
        // Puedes agregar un else para manejar el caso en que imageBitmap sea nulo.
    }
}



fun convertImageByteArrayToBitmap(imageData: ByteArray): Bitmap {
    return BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
}

@Composable
fun BitmapImage(bitmap: Bitmap) {
    Image(
        bitmap = bitmap.asImageBitmap(),
        contentDescription = null,
    )
}