package com.evertec.everteplacetopay.data.repository

import com.evertec.everteplacetopay.data.model.InfoTransaction
import com.evertec.everteplacetopay.data.model.Transaction
import com.evertec.everteplacetopay.data.model.TransactionEntity
import com.evertec.everteplacetopay.vo.Resource
import com.evertec.everteplacetopay.vo.json.output.GateWayQuery
import com.evertec.everteplacetopay.vo.json.output.PostJsonTransaction

interface Repository {

    suspend fun postGenerateTransaction(jsonTransaction: PostJsonTransaction): Resource<Transaction>

    suspend fun getAllTransaction(): Resource<List<TransactionEntity>>

    suspend fun getStateTransaction(gateWayQuery: GateWayQuery): Resource<InfoTransaction>

    suspend fun insertTransaction(transaction: TransactionEntity)

    fun printText(): String {
        return "hello"
    }
}