package com.example.condosamvvm.data.db.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object TipoPredioTable : Table("tipo_predio") {
    val idTipoPredio = integer("id_tipo_predio")
    val nomrePredio = varchar("nomre_predio", length = 255)
    override val primaryKey = PrimaryKey(idTipoPredio)
}
