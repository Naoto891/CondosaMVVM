package com.example.condosamvvm.presentation.vistaempleado

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class EmpleadoViewModel @Inject constructor(

) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


     fun onContratoSelected(xd: () -> Unit) {
        viewModelScope.launch(){
            _isLoading.value = true
            xd()
            _isLoading.value = false
        }
    }
}