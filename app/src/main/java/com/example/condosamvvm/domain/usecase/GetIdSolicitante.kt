package com.example.condosamvvm.domain.usecase

import com.example.condosamvvm.domain.repository.UsuarioRepository
import javax.inject.Inject

class GetIdSolicitante @Inject constructor(
    private val repository: UsuarioRepository
){
    suspend operator fun invoke(idPersona: Int): Boolean {
        return repository.getIdSolicitante(idPersona)
    }
}