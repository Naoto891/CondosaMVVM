package com.example.condosamvvm.presentation.vistacontrato.personal.firmarcontrato

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.condosamvvm.domain.model.Contrato
import com.example.condosamvvm.domain.model.ContratoData
import com.example.condosamvvm.domain.usecase.GetContratoData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class FirmarContratoPersonalViewModel @Inject constructor(

    private val getContratoData: GetContratoData,

): ViewModel() {
    private val _contratoData = MutableLiveData<ContratoData?>() // Cambio aquí: ContratoData? en lugar de ContratoData
    val contratoData : LiveData<ContratoData?> = _contratoData

    fun getContratoData(idContrato: Int) {
        viewModelScope.launch {
           _contratoData.value = getContratoData.invoke(idContrato)
        }
    }
}