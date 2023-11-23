package com.example.condosamvvm.presentation.vistacontrato.solicitante.firmarcontrato.contratosfirmados

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.condosamvvm.domain.usecase.GetContratosByIdSolicitante
import com.example.condosamvvm.domain.usecase.GetImagenSolicitante
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContratosFirmadosSolicitantesViewModel @Inject constructor(
    private val getImagenSolicitante: GetImagenSolicitante
) : ViewModel() {

    private val _imagenFirma = MutableLiveData<ByteArray>()
    val imagenFirma: LiveData<ByteArray> = _imagenFirma

    fun getImagenFirma(idContrato: Int, idSolicitante: Int) {
        viewModelScope.launch {
            val imagen = getImagenSolicitante(idContrato, idSolicitante)
                _imagenFirma.value = imagen

        }
    }
}
