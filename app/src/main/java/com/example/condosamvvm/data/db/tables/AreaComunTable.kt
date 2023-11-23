package com.example.condosamvvm.data.db.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object AreaComunTable : Table("area_comun") {
    val idAreaComun = integer("id_area_comun")
    val descripcion = varchar("descripcion", length = 50)

    override val primaryKey = PrimaryKey(idAreaComun)
}
