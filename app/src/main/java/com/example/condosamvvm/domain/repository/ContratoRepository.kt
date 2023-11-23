package com.example.condosamvvm.domain.repository

import com.example.condosamvvm.data.DatabaseFactory
import com.example.condosamvvm.domain.model.Contrato
import java.time.LocalDate

interface ContratoRepository {
    suspend fun getContratos(): List<Contrato>
    suspend fun firmarContratoEmpleado(id: Int, fechaFirmaPersonal: LocalDate, firmaPersonal: ByteArray): Boolean
    suspend fun firmarContratoSolicitante(id: Int, fechaFirmaSolicitante: LocalDate, firmaSolicitante: ByteArray): Boolean

    suspend fun getContratosByIdSolicitante(idPersona:Int) : List<Contrato>
    suspend fun getContratosByIdPersonal(idPersona:Int) : List<Contrato>

    suspend fun getImagenSolicitante(idContrato: Int, idSolicitante: Int): ByteArray?
   // suspend fun addContrato(title: String, body: String): Contrato?
   // suspend fun editContrato(id: Int, title: String, body: String): Boolean
}