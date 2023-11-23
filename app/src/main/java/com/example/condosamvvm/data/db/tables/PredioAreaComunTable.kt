package com.example.condosamvvm.data.db.tables

import com.example.condosamvvm.data.db.tables.ContratoTable.references
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object PredioAreaComunTable : Table("predio_area_comun") {
    val idPredio = integer("id_predio").references(PredioTable.idPredio)
    val idAreaComun = integer("id_area_comun").references(AreaComunTable.idAreaComun)
    val codigo = varchar("codigo", length = 6)
    val area = float("area")

    override val primaryKey = PrimaryKey(idPredio, idAreaComun)
}
