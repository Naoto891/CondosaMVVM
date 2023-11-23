package com.example.condosamvvm.di

import com.example.condosamvvm.data.db.dao.ContratoDao
import com.example.condosamvvm.data.db.dao.UsuarioDao
import com.example.condosamvvm.data.repository.ContratoRepositoryImpl
import com.example.condosamvvm.data.repository.UsuarioRepositoryImpl
import com.example.condosamvvm.domain.repository.ContratoRepository
import com.example.condosamvvm.domain.repository.UsuarioRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUsuarioRepository(): UsuarioRepository {
        return UsuarioRepositoryImpl(UsuarioDao())
    }

    @Provides
    @Singleton
    fun provideContratoRepository(): ContratoRepository {
        return ContratoRepositoryImpl(ContratoDao())
    }

}

