package com.example.condosamvvm.domain.usecase

import com.example.condosamvvm.domain.model.Contrato
import com.example.condosamvvm.domain.repository.ContratoRepository
import javax.inject.Inject

class GetContratos @Inject constructor(
    private val repository : ContratoRepository
){
    suspend  operator fun invoke() : List<Contrato> {
        return repository.getContratos()
    }
}