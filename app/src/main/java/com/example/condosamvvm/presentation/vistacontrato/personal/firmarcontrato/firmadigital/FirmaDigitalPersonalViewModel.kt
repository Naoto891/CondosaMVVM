package com.example.condosamvvm.presentation.vistacontrato.personal.firmarcontrato.firmadigital

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.condosamvvm.domain.usecase.FirmarPersonal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class FirmaDigitalPersonalViewModel @Inject constructor(
    private val firmarPersonal: FirmarPersonal
): ViewModel(){
    fun FirmarContratoPersonal(IdContrato: Int, FechaFirmaPersonal: LocalDate, signatureByteArray: ByteArray, xd: () -> Unit ){
        viewModelScope.launch {

            val firmar = firmarPersonal(IdContrato, FechaFirmaPersonal, signatureByteArray)

            if (firmar){
                xd()
            }

        }
    }
}