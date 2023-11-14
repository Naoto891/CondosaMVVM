package com.example.condosamvvm.domain.usecase

import com.example.condosamvvm.domain.repository.UsuarioRepository

import javax.inject.Inject

class CheckUsuario @Inject constructor(
    private val repository: UsuarioRepository
){

    suspend operator fun invoke(userName: String, password: String): Boolean {
        return repository.checkUsuario(userName, password)
    }

}