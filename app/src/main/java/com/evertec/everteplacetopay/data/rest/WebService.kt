package com.evertec.everteplacetopay.data.rest

import com.evertec.everteplacetopay.data.model.remote.GateWayQuery
import com.evertec.everteplacetopay.data.model.remote.GatewayQueryInput
import com.evertec.everteplacetopay.data.model.remote.ProcessTransactionInput
import com.evertec.everteplacetopay.data.model.remote.ProcessTransactionOutput
import retrofit2.http.Body
import retrofit2.http.POST


interface WebService {

    @POST("gateway/process")
    suspend fun postTransaction(@Body informationPay: ProcessTransactionOutput): ProcessTransactionInput

    @POST("gateway/query")
    suspend fun getStateTransaction(@Body gateWayQuery: GateWayQuery): GatewayQueryInput


}