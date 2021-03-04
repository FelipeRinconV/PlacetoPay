package com.evertec.evertec_test.vo.json.output

import Auth
import com.google.gson.annotations.SerializedName

/**
 * Representa el json que se envia a la api para consultar el estado de una transaccion
 */
data class GateWayQuery(
     val auth: Auth,
    val internalReference: Int
)