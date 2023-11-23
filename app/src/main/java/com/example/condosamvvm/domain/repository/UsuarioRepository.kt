package com.example.condosamvvm.domain.repository

import com.example.condosamvvm.domain.model.Usuario

interface UsuarioRepository {
    suspend fun searchUsuario(id: Int): Usuario?
    suspend fun checkUsuario(userName: String, password: String): Boolean
    suspend fun getIdPersonal(idPersona: Int): Boolean
    suspend fun getIdSolicitante(idPersona: Int): Boolean
    suspend fun obtenerUsuario(userName: String, password: String): Int?

    suspend fun getIdSolicitanteInt(idPersona: Int): Int?

    suspend fun getIdPersonalInt(idPersona: Int): Int?
}