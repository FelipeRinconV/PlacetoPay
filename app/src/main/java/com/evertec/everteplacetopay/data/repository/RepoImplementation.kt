package com.evertec.everteplacetopay.data.repository

import androidx.lifecycle.LiveData
import com.evertec.everteplacetopay.data.model.*
import com.evertec.everteplacetopay.data.model.remote.GateWayQuery
import com.evertec.everteplacetopay.data.model.remote.GatewayQueryInput
import com.evertec.everteplacetopay.data.model.remote.ProcessTransactionInput
import com.evertec.everteplacetopay.data.model.remote.ProcessTransactionOutput
import com.evertec.everteplacetopay.vo.Resource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class RepoImplementation @Inject constructor(private val dataSource: DataSource) : Repository {

    override suspend fun postGenerateTransaction(jsonTransaction: ProcessTransactionOutput): Resource<ProcessTransactionInput> {
        return dataSource.postGenerateTransaction(jsonTransaction)
    }

    override fun getAllTransaction(idUser: Long): LiveData<List<TransactionEntity>> {
        return dataSource.getAllTransaction(idUser)
    }

    override suspend fun getStateTransaction(gateWayQuery: GateWayQuery): Resource<GatewayQueryInput> {
        return dataSource.getUpdatedTransactionState(gateWayQuery)
    }

    override suspend fun insertTransaction(transaction: TransactionEntity) {
        dataSource.insertTransaction(transaction)
    }

    override suspend fun insertUser(userEntity: UserEntity): Long {
        return dataSource.insertUser(userEntity)
    }

    override suspend fun deleteTransaction(transactionEntity: TransactionEntity) {
        dataSource.deleteTransaction(transactionEntity)
    }

    override fun getUser(name: String, password: String): LiveData<UserEntity> {
        return dataSource.getUser(name, password)
    }

    override fun getTransactionById(id: String): LiveData<TransactionEntity> {
        return dataSource.getTransactionById(id)
    }


}