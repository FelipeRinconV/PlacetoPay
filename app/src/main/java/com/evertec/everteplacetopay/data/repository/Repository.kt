package com.evertec.everteplacetopay.data.repository

import PostJsonTransaction
import com.evertec.everteplacetopay.data.model.InfoTransaction
import com.evertec.everteplacetopay.data.model.Transaction
import com.evertec.everteplacetopay.data.model.TransactionEntity
import com.evertec.evertec_test.vo.Resource
import com.evertec.evertec_test.vo.json.output.GateWayQuery

interface Repository {

    suspend fun postGenerateTransaction(jsonTransaction: PostJsonTransaction): Resource<Transaction>

    suspend fun getAllTransaction(): Resource<List<TransactionEntity>>

    suspend fun getStateTransaction(gateWayQuery: GateWayQuery): Resource<InfoTransaction>

    suspend fun insertTransaction(transaction: TransactionEntity)
}