package com.example.condosamvvm.data.db.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object RolTable : Table("rol") {
    val idRol = integer("id_rol")
    val descripcion = varchar("descripcion", length = 60)
    override val primaryKey = PrimaryKey(idRol)
}
