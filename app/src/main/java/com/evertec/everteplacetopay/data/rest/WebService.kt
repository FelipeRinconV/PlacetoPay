package com.evertec.evertec_test.data.rest

import PostJsonTransaction
import com.evertec.everteplacetopay.data.model.InfoTransaction
import com.evertec.everteplacetopay.data.model.Transaction
import com.evertec.evertec_test.vo.json.output.GateWayQuery
import retrofit2.http.POST


interface WebService {

    @POST("gateway/process")
    suspend fun postTransaction(informationPay: PostJsonTransaction): Transaction

    @POST("gateway/query")
    suspend fun getStateTransaction(gateWayQuery: GateWayQuery): InfoTransaction


}