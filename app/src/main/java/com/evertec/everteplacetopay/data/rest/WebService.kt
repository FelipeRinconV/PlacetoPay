package com.evertec.everteplacetopay.data.rest

import com.evertec.everteplacetopay.data.model.InfoTransaction
import com.evertec.everteplacetopay.data.model.Transaction
import com.evertec.everteplacetopay.vo.json.output.GateWayQuery
import com.evertec.everteplacetopay.vo.json.output.PostJsonTransaction
import retrofit2.http.POST


interface WebService {

    @POST("gateway/process")
    suspend fun postTransaction(informationPay: PostJsonTransaction): Transaction

    @POST("gateway/query")
    suspend fun getStateTransaction(gateWayQuery: GateWayQuery): InfoTransaction


}