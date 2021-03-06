package com.evertec.everteplacetopay.data.repository

import androidx.lifecycle.MutableLiveData
import com.evertec.everteplacetopay.data.model.InfoTransaction
import com.evertec.everteplacetopay.data.model.Transaction
import com.evertec.everteplacetopay.data.model.TransactionEntity
import com.evertec.everteplacetopay.vo.Resource
import com.evertec.everteplacetopay.vo.json.output.GateWayQuery
import com.evertec.everteplacetopay.vo.json.output.PostJsonTransaction
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class RepoImplementation @Inject constructor(private val dataSource: DataSource) : Repository {

    override suspend fun postGenerateTransaction(jsonTransaction:PostJsonTransaction): Resource<Transaction> {
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