package com.evertec.everteplacetopay.data.repository

import com.evertec.everteplacetopay.data.local.TransactionDao
import com.evertec.everteplacetopay.data.model.*
import com.evertec.everteplacetopay.data.rest.WebService
import com.evertec.everteplacetopay.vo.Resource
import javax.inject.Inject

class DataSource @Inject constructor(
    private val transactionDao: TransactionDao,
    private val webService: WebService
) {

    suspend fun postGenerateTransaction(postJsonTransaction: String): Resource<ProcessTransactionInput> {
        return Resource.Success(webService.postTransaction(postJsonTransaction))
    }

    suspend fun getUpdatedTransactionState(gateWayQuery: String): Resource.Success<GatewayQueryInput> {
        return Resource.Success(webService.getStateTransaction(gateWayQuery))
    }

    suspend fun insertTransaction(transactionEntity: TransactionEntity) {
        transactionDao.insertTransaction(transactionEntity)
    }

    suspend fun getAllTransaction(): Resource<List<TransactionEntity>> {
        return Resource.Success(transactionDao.getAllTransactions())
    }

}