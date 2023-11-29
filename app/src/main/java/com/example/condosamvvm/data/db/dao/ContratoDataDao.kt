package com.example.condosamvvm.data.db.dao

import com.example.condosamvvm.data.DatabaseFactory.dbQuery
import com.example.condosamvvm.data.db.tables.*
import com.example.condosamvvm.domain.model.ContratoData
import com.example.condosamvvm.domain.model.AreaComun
import org.jetbrains.exposed.sql.*
import java.io.Serializable
import java.util.Date


class ContratoDataDao {
    suspend fun getContratoData(idContrato: Int): ContratoData? = dbQuery {
        val persona1Alias = PersonaTable.alias("persona1")
        val persona2Alias = PersonaTable.alias("persona2")
        val tdocu1Alias = TipoDocumentoTable.alias("tdocu1")
        val tdocu2Alias = TipoDocumentoTable.alias("tdocu2")

        val row = (ContratoTable
            .fullJoin(SolicitanteTable, { ContratoTable.idSolicitante }, { SolicitanteTable.idSolicitante })
            .fullJoin(persona1Alias, { SolicitanteTable.idPersona }, { persona1Alias[PersonaTable.idPersona] })
            .fullJoin(tdocu1Alias, { persona1Alias[PersonaTable.idTipoDocumento] }, { tdocu1Alias[TipoDocumentoTable.idTipoDocumento] })
            .fullJoin(PersonalTable, { ContratoTable.idPersonal }, { PersonalTable.idPersonal })
            .fullJoin(persona2Alias, { PersonalTable.idPersona }, { persona2Alias[PersonaTable.idPersona] })
            .fullJoin(tdocu2Alias, { persona2Alias[PersonaTable.idTipoDocumento] }, { tdocu2Alias[TipoDocumentoTable.idTipoDocumento] })
            .fullJoin(SolicitudCotizacionTable, { ContratoTable.idSolicitudCotizacion }, { SolicitudCotizacionTable.idSolicitudCotizacion })
            .fullJoin(SolicitudTable, { SolicitudCotizacionTable.idSolicitud }, { SolicitudTable.idSolicitud })
            .fullJoin(ServicioTable, { SolicitudTable.idServicio }, { ServicioTable.idServicio })
            .fullJoin(PredioTable, { SolicitudTable.idPredio }, { PredioTable.idPredio })
            .fullJoin(TipoPredioTable, { PredioTable.idTipoPredio }, { TipoPredioTable.idTipoPredio })
            .fullJoin(UbigeoTable, { PredioTable.idUbigeo }, { UbigeoTable.idUbigeo })
            .slice(
                persona1Alias[PersonaTable.apellidoPaterno],
                persona1Alias[PersonaTable.apellidoMaterno],
                persona1Alias[PersonaTable.nombres],
                tdocu1Alias[TipoDocumentoTable.descripcion],
                persona1Alias[PersonaTable.nDocumento],

                persona2Alias[PersonaTable.apellidoPaterno],
                persona2Alias[PersonaTable.apellidoMaterno],
                persona2Alias[PersonaTable.nombres],
                tdocu2Alias[TipoDocumentoTable.descripcion],
                persona2Alias[PersonaTable.nDocumento],

                SolicitudCotizacionTable.idSolicitudCotizacion,
                SolicitudCotizacionTable.fechaCotizacion,
                SolicitudCotizacionTable.importe,

                SolicitudTable.idSolicitud,
                SolicitudTable.fechaSolicitud,
                SolicitudTable.idServicio,
                ServicioTable.descripcion,
                SolicitudTable.numCasas,
                SolicitudTable.cantAComunes,
                SolicitudTable.cantAdministracion,
                SolicitudTable.cantPlimpieza,
                SolicitudTable.cantJardineria,
                SolicitudTable.cantVigilantes,

                PredioTable.idPredio,
                PredioTable.descripcion,
                TipoPredioTable.nomrePredio,
                PredioTable.ruc,
                PredioTable.direccion,
                UbigeoTable.departamento,
                UbigeoTable.provincia,
                UbigeoTable.distrito,
                UbigeoTable.superficie,
                UbigeoTable.altitud,
                UbigeoTable.latitud,
                UbigeoTable.longitud
            )
            .select { ContratoTable.idContrato eq idContrato }
            .singleOrNull())

        var idPredioGuardado: Int

        row?.let {

            idPredioGuardado = it[PredioTable.idPredio] ?: 0

            val areasComunes = PredioAreaComunTable
                .join(AreaComunTable, JoinType.INNER, additionalConstraint = { PredioAreaComunTable.idAreaComun eq AreaComunTable.idAreaComun })
                .select { PredioAreaComunTable.idPredio eq idPredioGuardado }
                .map {
                    AreaComun(
                        it[AreaComunTable.descripcion] ?: "",
                        it[PredioAreaComunTable.area] ?: 0.0f
                    )
                }

            ContratoData(
                it[persona1Alias[PersonaTable.apellidoPaterno]] ?: "",
                it[persona1Alias[PersonaTable.apellidoMaterno]] ?: "",
                it[persona1Alias[PersonaTable.nombres]] ?: "",
                it[tdocu1Alias[TipoDocumentoTable.descripcion]] ?: "",
                it[persona1Alias[PersonaTable.nDocumento]] ?: "",

                it[persona2Alias[PersonaTable.apellidoPaterno]] ?: "",
                it[persona2Alias[PersonaTable.apellidoMaterno]] ?: "",
                it[persona2Alias[PersonaTable.nombres]] ?: "",
                it[tdocu2Alias[TipoDocumentoTable.descripcion]] ?: "",
                it[persona2Alias[PersonaTable.nDocumento]] ?: "",

                it[SolicitudCotizacionTable.idSolicitudCotizacion] ?: 0,
                it[SolicitudCotizacionTable.fechaCotizacion] ?: Date(),
                it[SolicitudCotizacionTable.importe] ?: 0.0f,

                it[SolicitudTable.idSolicitud] ?: 0,
                it[SolicitudTable.fechaSolicitud] ?: Date(),
                it[SolicitudTable.idServicio] ?: 0,
                it[ServicioTable.descripcion] ?: "",
                it[SolicitudTable.numCasas] ?: 0,
                it[SolicitudTable.cantAComunes] ?: 0,
                it[SolicitudTable.cantAdministracion] ?: 0,
                it[SolicitudTable.cantPlimpieza] ?: 0,
                it[SolicitudTable.cantJardineria] ?: 0,
                it[SolicitudTable.cantVigilantes] ?: 0,

                it[PredioTable.idPredio] ?: 0,
                it[PredioTable.descripcion] ?: "",
                it[TipoPredioTable.nomrePredio] ?: "",
                it[PredioTable.ruc] ?: "",
                it[PredioTable.direccion] ?: "",
                it[UbigeoTable.departamento] ?: "",
                it[UbigeoTable.provincia] ?: "",
                it[UbigeoTable.distrito] ?: "",
                it[UbigeoTable.superficie] ?: 0.0f,
                it[UbigeoTable.altitud] ?: 0.0f,
                it[UbigeoTable.latitud] ?: 0.0f,
                it[UbigeoTable.longitud] ?: 0.0f,
                areasComunes = areasComunes
            )
        }
    }

}
