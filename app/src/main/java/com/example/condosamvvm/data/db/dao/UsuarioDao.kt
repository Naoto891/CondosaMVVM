package com.example.condosamvvm.data.db.dao

import com.example.condosamvvm.data.DatabaseFactory.dbQuery
import com.example.condosamvvm.data.db.tables.PersonaTable
import com.example.condosamvvm.data.db.tables.PersonalTable
import com.example.condosamvvm.data.db.tables.SolicitanteTable
import com.example.condosamvvm.data.db.tables.UsuarioTable
import com.example.condosamvvm.domain.model.Usuario
import org.jetbrains.exposed.sql.*


class UsuarioDao {

    private fun resultRowToUsuario(row: ResultRow) = Usuario(
        idUsuario = row[UsuarioTable.idUsuario],
        userName = row[UsuarioTable.userName],
        password = row[UsuarioTable.password],
        idPersona= row[UsuarioTable.idPersona]
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

    suspend fun obtenerUsuario(userName: String, password: String): Int? = dbQuery {
        UsuarioTable
            .select {UsuarioTable.userName eq userName and (UsuarioTable.password eq password)}
            .singleOrNull()
            ?.get(UsuarioTable.idPersona)
    }

    suspend fun getIdSolicitante(idPersona: Int): Boolean = dbQuery {
        SolicitanteTable
            .select { SolicitanteTable.idPersona eq idPersona }
            .count() > 0
    }

    suspend fun getIdPersonal(idPersona: Int): Boolean = dbQuery {
        PersonalTable
            .select { PersonalTable.idPersona eq idPersona }
            .count() >0
    }

    suspend fun checkUsuario(userName: String, password: String): Boolean = dbQuery {
        UsuarioTable
            .select { UsuarioTable.userName eq userName and (UsuarioTable.password eq password) }
            .count() > 0
    }

    suspend fun getIdSolicitanteInt(idPersona: Int): Int? = dbQuery {
        (PersonaTable innerJoin SolicitanteTable)
            .select { PersonaTable.idPersona eq idPersona }
            .singleOrNull()
            ?.get(SolicitanteTable.idSolicitante)
    }

    suspend fun getIdPersonalInt(idPersona: Int): Int? = dbQuery {
        (PersonaTable innerJoin  PersonalTable)
            .select { PersonaTable.idPersona eq idPersona }
            .singleOrNull()
            ?.get(PersonalTable.idPersonal)
    }


}
