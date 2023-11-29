package com.example.condosamvvm.domain.model

import com.example.condosamvvm.data.db.dao.ContratoDataDao
import java.io.Serializable

data class ContratoData(
    val solicitanteAP: String,
    val solicitanteAM: String,
    val solicitanteNM: String,
    val solicitanteTipDoc: String,
    val solicitanteDoc: String,

    val personalAP: String,
    val personalAM: String,
    val personalNM: String,
    val personalTipDoc: String,
    val perosonalDoc: String,

    val cotizacionId: Int,
    val fechaCotizacion: Serializable,
    val cotizacionImporte: Float,

    val solicitudId: Int,
    val solicitudFecha: Serializable,
    val solicitudServicio: Int,
    val servicioDesc: String,
    val solicitudNumCas: Int,
    val solicitudCantAAcomun: Int,
    val solicitudCantAdmin: Int,
    val solicitudCantLimp: Int,
    val solicitudCantJard: Int,
    val solicitudCantSegur: Int,

    val predioId: Int,
    val predioDesc: String,
    val predioTipo: String,
    val predioRuc: String,
    val predioDirec: String,
    val predioUbiDepar: String,
    val predioUbiProv: String,
    val predioUbiDistr: String,
    val predioUbiSuper: Float,
    val predioUbiAlti: Float,
    val predioUbiLati: Float,
    val predioUbiLongi: Float,

    val areasComunes: List<AreaComun>,
)