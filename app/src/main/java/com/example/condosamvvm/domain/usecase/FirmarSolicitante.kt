package com.example.condosamvvm.domain.usecase

import com.example.condosamvvm.domain.repository.ContratoRepository
import java.time.LocalDate
import javax.inject.Inject

class FirmarSolicitante @Inject constructor(
    private val repository: ContratoRepository
){
    suspend operator fun invoke(id: Int, fechaFirmaSolicitante: LocalDate, firmaSolicitante: ByteArray): Boolean {
        return repository.firmarContratoSolicitante(id, fechaFirmaSolicitante, firmaSolicitante)
    }
}