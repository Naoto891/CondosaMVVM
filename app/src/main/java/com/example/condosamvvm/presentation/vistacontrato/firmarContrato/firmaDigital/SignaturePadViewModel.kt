package com.example.condosamvvm.presentation.vistacontrato.firmarContrato.firmaDigital

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.condosamvvm.domain.model.Contrato
import com.example.condosamvvm.domain.usecase.FirmarPersonal
import com.example.condosamvvm.domain.usecase.GetContratos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class SignaturePadViewModel @Inject constructor(
    private val firmarPersonal: FirmarPersonal,
    private val getContratos: GetContratos
) : ViewModel(){


    private val _contratos = MutableLiveData<List<Contrato>>()



    fun getcontratos() {
        viewModelScope.launch {
            _contratos.value = getContratos()
        }
    }

    fun FirmarContratoEmpleado(IdContrato: Int , FechaFirmaPersonal: LocalDate, signatureByteArray: ByteArray,xd: () -> Unit ){
        viewModelScope.launch {

            val firmar = firmarPersonal(IdContrato, FechaFirmaPersonal, signatureByteArray)

            if (firmar){
                getContratos()
                xd()
            }

        }
    }



}