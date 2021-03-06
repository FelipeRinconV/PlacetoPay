package com.evertec.everteplacetopay.data.repository

import com.evertec.everteplacetopay.data.model.*
import com.evertec.everteplacetopay.vo.Resource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class RepoImplementation @Inject constructor(private val dataSource: DataSource) : Repository {

    override suspend fun postGenerateTransaction(jsonTransaction: String): Resource<ProcessTransactionInput> {
        return dataSource.postGenerateTransaction(jsonTransaction)
    }

    override suspend fun getAllTransaction(): Resource<List<TransactionEntity>> {
        return dataSource.getAllTransaction()
    }

    override suspend fun getStateTransaction(gateWayQuery: String): Resource<GatewayQueryInput> {
        return dataSource.getUpdatedTransactionState(gateWayQuery)
    }

    override suspend fun insertTransaction(transaction: TransactionEntity) {
        dataSource.insertTransaction(transaction)
    }
}