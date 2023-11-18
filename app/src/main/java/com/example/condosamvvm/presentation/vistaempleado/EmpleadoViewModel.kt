package com.example.condosamvvm.presentation.vistaempleado

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.condosamvvm.domain.usecase.GetIdPersonalInt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class EmpleadoViewModel @Inject constructor(
    private val getIdPersonalInt: GetIdPersonalInt
) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isPersona = MutableLiveData<Int>()
    val isPersona: LiveData<Int> = _isPersona

     fun getPERSONAL(idPersona: Int) {
         viewModelScope.launch {
             _isPersona.value = getIdPersonalInt(idPersona)
         }
    }

     fun onContratoSelected(xd: () -> Unit) {
        viewModelScope.launch(){
            _isLoading.value = true
            xd()
            _isLoading.value = false
        }
    }
}