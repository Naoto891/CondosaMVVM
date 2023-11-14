package com.example.condosamvvm.data.db.dao

import com.example.condosamvvm.data.DatabaseFactory.dbQuery
import com.example.condosamvvm.data.db.tables.UsuarioTable
import com.example.condosamvvm.domain.model.Usuario
import org.jetbrains.exposed.sql.*


class UsuarioDao {

    private fun resultRowToUsuario(row: ResultRow) = Usuario(
        idUsuario = row[UsuarioTable.idUsuario],
        userName = row[UsuarioTable.userName],
        password = row[UsuarioTable.password]
    )

    suspend fun getUsuarios() : List<Usuario> = dbQuery {
        UsuarioTable
            .selectAll()
            .map(::resultRowToUsuario)
    }

    suspend fun searchUsuario(id: Int): Usuario?  = dbQuery{
        UsuarioTable
            .select {UsuarioTable.idUsuario eq id}
            .map(::resultRowToUsuario)
            .singleOrNull()
    }



    suspend fun checkUsuario(userName: String, password: String): Boolean = dbQuery {
        UsuarioTable
            .select { UsuarioTable.userName eq userName and (UsuarioTable.password eq password) }
            .count() > 0
    }


}
