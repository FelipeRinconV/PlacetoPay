package com.evertec.everteplacetopay.data.repository

import PostJsonTransaction
import com.evertec.everteplacetopay.data.model.InfoTransaction
import com.evertec.everteplacetopay.data.model.Transaction
import com.evertec.everteplacetopay.data.model.TransactionEntity
import com.evertec.evertec_test.vo.Resource
import com.evertec.evertec_test.vo.json.output.GateWayQuery

class RepoImplementation  (private val dataSource: DataSource) : Repository {

    override suspend fun postGenerateTransaction(jsonTransaction: PostJsonTransaction): Resource<Transaction> {
        return dataSource.postGenerateTransaction(jsonTransaction)
    }

    override suspend fun getAllTransaction(): Resource<List<TransactionEntity>> {
        return dataSource.getAllTransaction()
    }

    override suspend fun getStateTransaction(gateWayQuery: GateWayQuery): Resource<InfoTransaction> {
        return dataSource.getUpdatedTransactionState(gateWayQuery)
    }

    override suspend fun insertTransaction(transaction: TransactionEntity) {
        dataSource.insertTransaction(transaction)
    }
}