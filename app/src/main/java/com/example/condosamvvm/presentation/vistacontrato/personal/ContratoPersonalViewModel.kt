package com.example.condosamvvm.presentation.vistacontrato.personal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.condosamvvm.domain.model.Contrato
import com.example.condosamvvm.domain.usecase.GetContratosByIdPersonal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContratoPersonalViewModel @Inject constructor(
    private val getContratosByIdPersonal: GetContratosByIdPersonal
): ViewModel(){

    private val _isSelectedIndex = MutableLiveData<Int>()
    val isSelectedIndex: LiveData<Int> = _isSelectedIndex

    private val _contratos = MutableLiveData<List<Contrato>>()
    val contratos: LiveData<List<Contrato>> = _contratos

    fun selectedIndexChanged(index : Int){
        _isSelectedIndex.value = index
    }

    fun getContratosPersonal(idPersona : Int) {
        viewModelScope.launch {
            _contratos.value = getContratosByIdPersonal(idPersona)
        }
    }

}