package com.example.condosamvvm.data.db.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object TipoDocumentoTable : Table("tipo_documento") {
    val idTipoDocumento = integer("id_tipo_documento")
    val descripcion = varchar("descripcion", length = 20)

    override val primaryKey = PrimaryKey(idTipoDocumento)
}
