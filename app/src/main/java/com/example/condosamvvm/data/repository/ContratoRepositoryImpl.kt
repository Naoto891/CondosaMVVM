package com.example.condosamvvm.data.repository

import com.example.condosamvvm.data.db.dao.ContratoDao
import com.example.condosamvvm.domain.model.Contrato
import com.example.condosamvvm.domain.repository.ContratoRepository
import java.time.LocalDate
import javax.inject.Inject

class ContratoRepositoryImpl @Inject constructor(
    private val dao : ContratoDao
) : ContratoRepository{

    override suspend fun getContratos(): List<Contrato> {
       return dao.getContratos()
    }

    override suspend fun searchContrato(id: Int): Contrato? {
       return dao.searchContrato(id)
    }

    override suspend fun firmarContratoEmpleado(id: Int, fechaFirmaPersonal: LocalDate): Boolean {
       return dao.firmarContratoEmpleado(id,fechaFirmaPersonal)
    }
}