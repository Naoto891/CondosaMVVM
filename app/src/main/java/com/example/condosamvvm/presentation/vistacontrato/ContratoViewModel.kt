package com.example.condosamvvm.presentation.vistacontrato

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.condosamvvm.domain.model.Contrato
import com.example.condosamvvm.domain.usecase.GetContratos
import com.example.condosamvvm.domain.usecase.SearchContrato
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ContratoViewModel @Inject constructor(
    private val getContratos: GetContratos,
    private val searchContrato: SearchContrato
): ViewModel() {

    private val _isSelectedIndex = MutableLiveData<Int>()
    val isSelectedIndex: LiveData<Int> = _isSelectedIndex

    private val _contratos = MutableLiveData<List<Contrato>>()
    val contratos: LiveData<List<Contrato>> = _contratos



    init {
        viewModelScope.launch {
            _contratos.value = getContratos()
        }
    }

    fun selectedIndexChanged(index : Int){
        _isSelectedIndex.value = index
    }


    fun onNextButton(contratoId: Int,xd: (Int) -> Unit) {
        viewModelScope.launch{
            xd(contratoId)
        }

    }

}