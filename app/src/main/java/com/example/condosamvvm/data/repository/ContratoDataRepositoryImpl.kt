package com.example.condosamvvm.data.repository

import com.example.condosamvvm.data.DatabaseFactory
import com.example.condosamvvm.data.db.dao.ContratoDataDao
import com.example.condosamvvm.data.db.dao.UsuarioDao
import com.example.condosamvvm.data.db.tables.UsuarioTable
import com.example.condosamvvm.domain.model.ContratoData
import com.example.condosamvvm.domain.model.Usuario
import com.example.condosamvvm.domain.repository.ContratoDataRepository
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select


import javax.inject.Inject

class ContratoDataRepositoryImpl @Inject constructor(
    private val dao: ContratoDataDao
) : ContratoDataRepository {

    override suspend fun getContratoData(idContrato: Int): ContratoData?{
        return dao.getContratoData(idContrato)
    }

}
