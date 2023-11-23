package com.example.condosamvvm.domain.usecase

import com.example.condosamvvm.domain.repository.ContratoRepository
import javax.inject.Inject

class GetImagenSolicitante @Inject constructor(
    private val repository: ContratoRepository
) {

    suspend operator fun invoke(idContrato: Int, idSolicitante: Int) : ByteArray? {
        return repository.getImagenSolicitante(idContrato, idSolicitante)
    }

}