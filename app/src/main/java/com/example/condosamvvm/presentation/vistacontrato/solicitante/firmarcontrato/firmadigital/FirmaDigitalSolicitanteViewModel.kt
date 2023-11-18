package com.example.condosamvvm.presentation.vistacontrato.solicitante.firmarcontrato.firmadigital

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.condosamvvm.domain.usecase.FirmarSolicitante
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class FirmaDigitalSolicitanteViewModel @Inject constructor(
    private val firmarSolicitante: FirmarSolicitante
): ViewModel(){

    fun FirmarContratoSolicitante(IdContrato: Int, FechaFirmaPersonal: LocalDate, signatureByteArray: ByteArray, xd: () -> Unit ){
        viewModelScope.launch {

             val firmar = firmarSolicitante(IdContrato, FechaFirmaPersonal, signatureByteArray)

            if (firmar){
                xd()
            }

        }
    }
}