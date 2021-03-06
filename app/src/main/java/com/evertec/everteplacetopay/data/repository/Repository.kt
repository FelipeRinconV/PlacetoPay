package com.evertec.everteplacetopay.data.repository

import com.evertec.everteplacetopay.data.model.*
import com.evertec.everteplacetopay.vo.Resource

interface Repository {


    suspend fun postGenerateTransaction(jsonTransaction: String): Resource<ProcessTransactionInput>

    suspend fun getAllTransaction(): Resource<List<TransactionEntity>>

    suspend fun getStateTransaction(jsonGateWayQuery: String): Resource<GatewayQueryInput>

    suspend fun insertTransaction(transaction: TransactionEntity)

    fun printText(): String {
        return "hello"
    }
}