package com.example.condosamvvm.data.db.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object ContratoTable : Table("contrato") {
    val idContrato = integer("id_contrato").autoIncrement()
    val idSolicitudCotizacion = integer("id_solicitud_cotizacion")
    val idPersonal = integer("id_personal").references(PersonalTable.idPersonal)
    val idSolicitante = integer("id_solicitante").references(SolicitanteTable.idSolicitante)
    val fechaContrato = date("fecha_contrato")
    val fechaFirmaSolicitante = date("fecha_firma_solicitante")
    val fechaFirmaPersonal = date("fecha_firma_personal")
    val fechaRegistro = date("fecha_registro")
    val minuta = varchar("minuta", length = 255)
    val firmaSolicitante = binary("firma_solicitante") // Agregado: tipo bytea
    val firmaPersonal = binary("firma_personal") // Agregado: tipo bytea

    override val primaryKey = PrimaryKey(idContrato)
}
