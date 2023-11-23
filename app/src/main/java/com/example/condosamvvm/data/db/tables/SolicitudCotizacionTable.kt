package com.example.condosamvvm.data.db.tables

import com.example.condosamvvm.data.db.tables.ContratoTable.references
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object SolicitudCotizacionTable : Table("solicitud_cotizacion") {

    val idSolicitud = integer("id_solicitud").references(SolicitudTable.idSolicitud)
    val idPersonal= integer("id_personal").references(PersonalTable.idPersonal)
    val fechaCotizacion = date("fecha_cotizacion")
    val importe = float("importe")
    val idSolicitudCotizacion = integer("id_solicitud_cotizacion").autoIncrement()
    val idEstado = integer("id_estado").references(EstadoTable.idEstado)

    override val primaryKey = PrimaryKey(idSolicitudCotizacion)
}
