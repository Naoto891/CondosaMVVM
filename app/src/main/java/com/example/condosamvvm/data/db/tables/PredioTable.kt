package com.example.condosamvvm.data.db.tables

import com.example.condosamvvm.data.db.tables.ContratoTable.references
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object PredioTable : Table("predio") {
    val idPredio = integer("id_predio").autoIncrement()
    val idTipoPredio = integer("id_tipo_predio").references(TipoPredioTable.idTipoPredio)
    val descripcion = varchar("descripcion", length = 80)
    val ruc = varchar("ruc", length = 20)
    val telefono = varchar("telefono", length = 12)
    val direccion = varchar("direccion", length = 100)
    val idUbigeo = varchar("idubigeo", length = 6).references(UbigeoTable.idUbigeo)
    val idPersona = integer("id_persona").references(PersonaTable.idPersona)
    val urlImagen = varchar("url_imagen", length = 120)
    val totalMdu = integer("total_mdu")

    override val primaryKey = PrimaryKey(idPredio)
}
