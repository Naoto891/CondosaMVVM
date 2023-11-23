package com.example.condosamvvm.data.db.tables

import org.jetbrains.exposed.sql.Table

object SolicitanteTable : Table("solicitante") {
    val idSolicitante = integer("id_solicitante").autoIncrement()
    val idPersona = integer("id_persona").references(PersonaTable.idPersona)
    val idRol = integer("id_rol")
    val telefono = integer("telefono")
    val correo = varchar("correo", length = 255)

    override val primaryKey = PrimaryKey(idSolicitante)


}
