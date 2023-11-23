package com.example.condosamvvm.data.db.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object UbigeoTable : Table("ubigeo") {
    val idUbigeo = varchar("idubigeo", length = 6)
    val departamento = varchar("departamento", length = 60)
    val provincia = varchar("provincia", length = 60)
    val distrito = varchar("distrito", length = 60)
    val superficie = float("superficie")
    val altitud = float("altitud")
    val latitud = float("latitud")
    val longitud = float("longitud")

    override val primaryKey = PrimaryKey(idUbigeo)
}
