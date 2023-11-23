package com.example.condosamvvm.data.db.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object ServicioTable : Table("servicio") {
    val idServicio = integer("id_servicio")
    val descripcion = varchar("descripcion", length = 50)
    val precio = float("precio")

    override val primaryKey = PrimaryKey(idServicio)
}
