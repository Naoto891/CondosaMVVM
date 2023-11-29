

package com.example.condosamvvm.data.repository

import com.example.condosamvvm.data.db.dao.UsuarioDao
import com.example.condosamvvm.domain.model.Usuario
import com.example.condosamvvm.domain.repository.UsuarioRepository


import javax.inject.Inject

class UsuarioRepositoryImpl @Inject constructor(
    private val dao: UsuarioDao
) : UsuarioRepository {

    override suspend fun searchUsuario(id: Int): Usuario? {
        return dao.searchUsuario(id)
    }


    override suspend fun checkUsuario(userName: String, password: String): Boolean {
        return dao.checkUsuario(userName,password)
    }

    override suspend fun getIdPersonal(idPersona: Int): Boolean {
        return dao.getIdPersonal(idPersona)
    }

    override suspend fun getIdSolicitante(idPersona: Int): Boolean {
        return dao.getIdSolicitante(idPersona)
    }

    override suspend fun obtenerUsuario(userName: String, password: String): Int? {
        return dao.obtenerUsuario(userName,password)
    }

    override suspend fun getIdSolicitanteInt(idPersona: Int): Int? {
        return dao.getIdSolicitanteInt(idPersona)
    }

    override suspend fun getIdPersonalInt(idPersona: Int): Int? {
        return dao.getIdPersonalInt(idPersona)
    }

}
