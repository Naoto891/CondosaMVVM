package com.example.condosamvvm.domain.usecase

import com.example.condosamvvm.domain.model.ContratoData
import com.example.condosamvvm.domain.repository.ContratoDataRepository

import javax.inject.Inject

class GetContratoData @Inject constructor(
    private val repository: ContratoDataRepository
){

    suspend operator fun invoke(idContrato: Int): ContratoData ?{
        return repository.getContratoData(idContrato)
    }

}