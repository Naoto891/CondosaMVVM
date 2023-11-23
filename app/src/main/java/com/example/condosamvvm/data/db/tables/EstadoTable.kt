package com.example.condosamvvm.data.db.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object EstadoTable : Table("estado") {
    val idEstado = integer("id_estado")
    val descripcion = varchar("descripcion", length = 15)
    override val primaryKey = PrimaryKey(idEstado)
}
