package com.evertec.everteplacetopay.data.rest

import com.evertec.everteplacetopay.data.model.*
import retrofit2.http.Body
import retrofit2.http.POST


interface WebService {

    @POST("gateway/process")
    suspend fun postTransaction(@Body informationPay: String): ProcessTransactionInput

    @POST("gateway/query")
    suspend fun getStateTransaction(@Body gateWayQuery: String): GatewayQueryInput


}