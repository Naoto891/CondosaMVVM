package com.example.condosamvvm.domain.repository

import com.example.condosamvvm.domain.model.Usuario

interface UsuarioRepository {
    suspend fun searchUsuario(id: Int): Usuario?
    suspend fun checkUsuario(userName: String, password: String): Boolean
}