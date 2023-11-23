package com.example.condosamvvm.domain.usecase

import com.example.condosamvvm.domain.repository.UsuarioRepository
import javax.inject.Inject

class GetIdPersonalInt @Inject constructor(
    private val repository: UsuarioRepository
) {
    suspend operator fun invoke(idPersona : Int) : Int? {
        return repository.getIdPersonalInt(idPersona)
    }
}