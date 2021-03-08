package com.evertec.everteplacetopay.data.repository

import androidx.lifecycle.LiveData
import com.evertec.everteplacetopay.data.local.TransactionDao
import com.evertec.everteplacetopay.data.model.*
import com.evertec.everteplacetopay.data.model.remote.GateWayQuery
import com.evertec.everteplacetopay.data.model.remote.GatewayQueryInput
import com.evertec.everteplacetopay.data.model.remote.ProcessTransactionInput
import com.evertec.everteplacetopay.data.model.remote.ProcessTransactionOutput
import com.evertec.everteplacetopay.data.rest.WebService
import com.evertec.everteplacetopay.vo.Resource
import javax.inject.Inject

class DataSource @Inject constructor(
    private val transactionDao: TransactionDao,
    private val webService: WebService
) {

    suspend fun postGenerateTransaction(postJsonTransaction: ProcessTransactionOutput): Resource<ProcessTransactionInput> {
        return Resource.Success(webService.postTransaction(postJsonTransaction))
    }

    suspend fun getUpdatedTransactionState(gateWayQuery: GateWayQuery): Resource.Success<GatewayQueryInput> {
        return Resource.Success(webService.getStateTransaction(gateWayQuery))
    }

    suspend fun insertTransaction(transactionEntity: TransactionEntity) {
        transactionDao.insertTransaction(transactionEntity)
    }

    fun getAllTransaction(idUser: Long): LiveData<List<TransactionEntity>> {
        return transactionDao.getAllTransactions(idUser)
    }

    suspend fun deleteTransaction(transactionEntity: TransactionEntity) {
        transactionDao.deleteTransaction(transactionEntity)
    }

    fun getUser(name: String, password: String): LiveData<UserEntity> {
        return transactionDao.getUser(name, password)
    }

    suspend fun insertUser(userEntity: UserEntity): Long {
        return transactionDao.insertUser(userEntity)
    }


    fun getTransactionById(id: String): LiveData<TransactionEntity> {
        return transactionDao.getTransactionById(id)
    }


}