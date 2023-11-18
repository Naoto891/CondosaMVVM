package com.example.condosamvvm.data.db.tables

import org.jetbrains.exposed.sql.Table

object UsuarioTable : Table("usuario") {
    val idUsuario = integer("id_usuario").autoIncrement()
    val userName = varchar("user_name", length = 255)
    val password = varchar("password", length = 255)
    val idPersona = integer("id_persona").references(PersonaTable.idPersona)

    override val primaryKey = PrimaryKey(idUsuario)
}
