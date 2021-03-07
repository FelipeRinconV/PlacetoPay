package com.evertec.everteplacetopay.data.model

import com.evertec.everteplacetopay.vo.json.output.Auth

/**
 * Representa el json que se envia a la api para consultar el estado de una transaccion
 * [https://dev.placetopay.com/rest/gateway/query]
 */
data class GateWayQuery(
    val locale: String,
    val auth: Auth,
    val internalReference: Int
)