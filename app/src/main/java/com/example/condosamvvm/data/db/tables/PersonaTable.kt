package com.example.condosamvvm.data.db.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object PersonaTable : Table("persona") {
    val idPersona = integer("id_persona").autoIncrement()
    val fechaNacimiento = date("fecha_nacimiento")
    val idTipoDocumento = integer("id_tipo_documento")
    val nombres = varchar("nombres", length = 255)
    val direccion = varchar("direccion", length = 255)
    val idUbigeo = varchar("idubigeo", length = 255)
    val nDocumento = varchar("ndocumento", length = 255)
    val apellidoPaterno = varchar("apellido_paterno", length = 255)
    val apellidoMaterno = varchar("apellido_materno", length = 255)

    override val primaryKey = PrimaryKey(idPersona)

}
