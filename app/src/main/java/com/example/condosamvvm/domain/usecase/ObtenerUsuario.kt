package com.example.condosamvvm.domain.usecase

import com.example.condosamvvm.domain.repository.UsuarioRepository
import javax.inject.Inject

class ObtenerUsuario @Inject constructor(
    private val repository: UsuarioRepository
){
    suspend operator fun invoke(userName: String, password: String) : Int?{
        return repository.obtenerUsuario(userName, password)
    }
}