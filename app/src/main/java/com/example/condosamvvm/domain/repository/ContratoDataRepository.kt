package com.example.condosamvvm.domain.repository

import com.example.condosamvvm.domain.model.ContratoData

interface ContratoDataRepository {
    suspend fun getContratoData(idContrato: Int): ContratoData?
}