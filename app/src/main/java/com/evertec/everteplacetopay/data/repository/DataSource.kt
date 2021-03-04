package com.evertec.everteplacetopay.data.repository

import PostJsonTransaction
import com.evertec.everteplacetopay.data.local.AppDatabase
import com.evertec.everteplacetopay.data.model.InfoTransaction
import com.evertec.everteplacetopay.data.model.Transaction
import com.evertec.everteplacetopay.data.model.TransactionEntity
import com.evertec.evertec_test.data.rest.WebService
import com.evertec.evertec_test.vo.Resource
import com.evertec.evertec_test.vo.json.output.GateWayQuery

class DataSource (private val appDatabase: AppDatabase, private val webService:WebService) {

    suspend fun postGenerateTransaction(postJsonTransaction: PostJsonTransaction): Resource<Transaction> {
        return Resource.Success(webService.postTransaction(postJsonTransaction))
    }

    suspend fun getUpdatedTransactionState(gateWayQuery: GateWayQuery): Resource.Success<InfoTransaction> {
        return Resource.Success(webService.getStateTransaction(gateWayQuery))
    }

    suspend fun insertTransaction(transactionEntity: TransactionEntity) {
        appDatabase.coktailsDao().insertTransaction(transactionEntity)
    }

    suspend fun getAllTransaction(): Resource<List<TransactionEntity>> {
        return Resource.Success(appDatabase.coktailsDao().getAllTransactions())
    }

}