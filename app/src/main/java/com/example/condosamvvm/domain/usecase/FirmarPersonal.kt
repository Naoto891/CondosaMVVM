package com.example.condosamvvm.domain.usecase

import com.example.condosamvvm.domain.repository.ContratoRepository
import java.time.LocalDate
import javax.inject.Inject

class FirmarPersonal @Inject constructor(
    private val repository: ContratoRepository
) {

    suspend operator fun invoke(idContrato: Int, fechaFirmaPersonal: LocalDate, firmaPersonal: ByteArray): Boolean {
        return repository.firmarContratoEmpleado(idContrato, fechaFirmaPersonal, firmaPersonal)
    }
}
