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



    override suspend fun firmarContratoEmpleado(id: Int, fechaFirmaPersonal: LocalDate, firmaPersonal: ByteArray): Boolean {
       return dao.firmarContratoEmpleado(id,fechaFirmaPersonal, firmaPersonal )
    }

    override suspend fun firmarContratoSolicitante(id: Int, fechaFirmaSolicitante: LocalDate, firmaSolicitante: ByteArray): Boolean {
       return dao.firmarContratoSolicitante(id, fechaFirmaSolicitante,firmaSolicitante)
    }

    override suspend fun getContratosByIdSolicitante(idPersona: Int): List<Contrato> {
        return dao.getContratosByIdSolicitante(idPersona)
    }

    override suspend fun getContratosByIdPersonal(idPersona: Int): List<Contrato> {
        return dao.getContratosByIdPersonal(idPersona)
    }

    override suspend fun getImagenSolicitante(idContrato: Int, idSolicitante: Int): ByteArray? {
        return dao.getImagenSolicitante(idContrato, idSolicitante)
    }


}