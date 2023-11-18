package com.example.condosamvvm.data.db.dao

import com.example.condosamvvm.data.DatabaseFactory.dbQuery
import com.example.condosamvvm.data.db.tables.ContratoTable
import com.example.condosamvvm.domain.model.Contrato

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import java.time.LocalDate

class ContratoDao {

    private fun resultRowToContrato(row: ResultRow) = Contrato(
        idContrato = row[ContratoTable.idContrato],
        idSolicitudCotizacion = row[ContratoTable.idSolicitudCotizacion],
        idPersonal = row[ContratoTable.idPersonal],
        idSolicitante = row[ContratoTable.idSolicitante],
        fechaContrato = row[ContratoTable.fechaContrato],
        fechaFirmaSolicitante = row[ContratoTable.fechaFirmaSolicitante],
        fechaFirmaPersonal = row[ContratoTable.fechaFirmaPersonal],
        fechaRegistro = row[ContratoTable.fechaRegistro],
        minuta = row[ContratoTable.minuta],
        firmaSolicitante = row[ContratoTable.firmaSolicitante],
        firmaPersonal = row[ContratoTable.firmaPersonal]
    )

    suspend fun getContratos(): List<Contrato> = dbQuery {
        ContratoTable
            .selectAll()
            .map(::resultRowToContrato)
    }

    suspend fun getContratosByIdSolicitante(idPersona:Int) : List<Contrato> = dbQuery {
        ContratoTable
            .select{ ContratoTable.idSolicitante eq idPersona}
            .map(::resultRowToContrato)
    }

    suspend fun getContratosByIdPersonal(idPersona:Int) : List<Contrato> = dbQuery {
        ContratoTable
            .select{ ContratoTable.idPersonal eq idPersona}
            .map(::resultRowToContrato)
    }

    suspend fun firmarContratoEmpleado(id: Int, fechaFirmaPersonal: LocalDate, firmaPersonal: ByteArray): Boolean = dbQuery {
        ContratoTable
            .update({ ContratoTable.idContrato eq id }){
                it[ContratoTable.fechaFirmaPersonal] = fechaFirmaPersonal
                it[ContratoTable.firmaPersonal] = firmaPersonal
            } > 0
    }

    suspend fun firmarContratoSolicitante(id: Int, fechaFirmaSolicitante: LocalDate, firmaSolicitante: ByteArray): Boolean = dbQuery {
        ContratoTable
            .update ({ ContratoTable.idContrato eq id }){
                it[ContratoTable.fechaFirmaSolicitante] = fechaFirmaSolicitante
                it[ContratoTable.firmaSolicitante] = firmaSolicitante
            } >0
    }


}