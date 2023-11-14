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

}
