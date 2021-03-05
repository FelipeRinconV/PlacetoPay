package com.evertec.everteplacetopay.data.repository

import com.evertec.everteplacetopay.data.local.TransactionDao
import com.evertec.everteplacetopay.data.model.InfoTransaction
import com.evertec.everteplacetopay.data.model.Transaction
import com.evertec.everteplacetopay.data.model.TransactionEntity
import com.evertec.everteplacetopay.data.rest.WebService
import com.evertec.everteplacetopay.vo.Resource
import com.evertec.everteplacetopay.vo.json.output.GateWayQuery
import com.evertec.everteplacetopay.vo.json.output.PostJsonTransaction
import javax.inject.Inject

class DataSource @Inject constructor(
    private val transactionDao: TransactionDao,
    private val webService: WebService
) {

    suspend fun postGenerateTransaction(postJsonTransaction: PostJsonTransaction): Resource<Transaction> {
        return Resource.Success(webService.postTransaction(postJsonTransaction))
    }

    suspend fun getUpdatedTransactionState(gateWayQuery: GateWayQuery): Resource.Success<InfoTransaction> {
        return Resource.Success(webService.getStateTransaction(gateWayQuery))
    }

    suspend fun insertTransaction(transactionEntity: TransactionEntity) {
        transactionDao.insertTransaction(transactionEntity)
    }

    suspend fun getAllTransaction(): Resource<List<TransactionEntity>> {
        return Resource.Success(transactionDao.getAllTransactions())
    }

}