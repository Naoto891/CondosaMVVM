package com.example.condosamvvm.domain.repository

import com.example.condosamvvm.domain.model.Contrato
import java.time.LocalDate

interface ContratoRepository {
    suspend fun getContratos(): List<Contrato>
    suspend fun searchContrato(id : Int): Contrato?
    suspend fun firmarContratoEmpleado(id: Int, fechaFirmaPersonal: LocalDate): Boolean
   // suspend fun addContrato(title: String, body: String): Contrato?
   // suspend fun editContrato(id: Int, title: String, body: String): Boolean
}