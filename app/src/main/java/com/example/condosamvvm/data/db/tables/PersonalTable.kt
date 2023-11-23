package com.example.condosamvvm.data.db.tables


import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object PersonalTable : Table("personal") {
    val idPersonal = integer("id_personal").autoIncrement()
    val idPersona = integer("id_persona").references(PersonaTable.idPersona)
    val idRol = integer("id_rol")
    val fechaContrato = date("fecha_contrato")
    val fechaCese = date("fecha_cese").nullable()

    override val primaryKey = PrimaryKey(idPersonal)

}
