package com.example.condosamvvm.presentation.vistasolicitante

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.condosamvvm.domain.usecase.GetIdSolicitanteInt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SolicitanteViewModel @Inject constructor(
    private val getIdSolicitanteInt: GetIdSolicitanteInt
)  : ViewModel(){

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isPersona = MutableLiveData<Int>()
    val isPersona: LiveData<Int> = _isPersona

    fun getSOLICITANTE(idPersona: Int) {
        viewModelScope.launch {
            _isPersona.value = getIdSolicitanteInt(idPersona)
        }
    }

   /* fun onContratoSelected(xd: (Int) -> Unit) {
        viewModelScope.launch(){
            _isLoading.value = true
            xd()
            _isLoading.value = false
        }
    }*/
}