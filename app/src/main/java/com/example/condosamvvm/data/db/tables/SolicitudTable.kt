package com.example.condosamvvm.data.db.tables

import com.example.condosamvvm.data.db.tables.ContratoTable.references
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object SolicitudTable : Table("solicitud") {
    val idSolicitud = integer("id_solicitud").autoIncrement()
    val idPredio= integer("id_predio").references(PredioTable.idPredio)
    val idSolicitante= integer("id_solicitante").references(SolicitanteTable.idSolicitante)
    val idServicio = integer("id_servicio").references(ServicioTable.idServicio)
    val areaPredio = float("area_predio")
    val numCasas = integer("num_casas")
    val cantAComunes = integer("cant_acomunes")
    val areaAComunes = integer("area_acomunes")
    val cantVigilantes = integer("cant_vigilantes")
    val cantPlimpieza = integer("cant_plimpieza")
    val cantAdministracion = integer("cant_administracion")
    val cantJardineria = integer("cant_jardineria")
    val fechaSolicitud = date("fecha_solicitud")
    val nombreSolicitante = varchar("nombre_solicitante", length = 70)

    override val primaryKey = PrimaryKey(idSolicitud)
}
