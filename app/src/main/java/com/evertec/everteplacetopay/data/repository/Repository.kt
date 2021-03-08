package com.evertec.everteplacetopay.data.repository

import androidx.lifecycle.LiveData
import com.evertec.everteplacetopay.data.model.*
import com.evertec.everteplacetopay.data.model.remote.GateWayQuery
import com.evertec.everteplacetopay.data.model.remote.GatewayQueryInput
import com.evertec.everteplacetopay.data.model.remote.ProcessTransactionInput
import com.evertec.everteplacetopay.data.model.remote.ProcessTransactionOutput
import com.evertec.everteplacetopay.vo.Resource

interface Repository {

    suspend fun postGenerateTransaction(jsonTransaction: ProcessTransactionOutput): Resource<ProcessTransactionInput>

    fun getAllTransaction(idUser: Long): LiveData<List<TransactionEntity>>

    suspend fun getStateTransaction(jsonGateWayQuery: GateWayQuery): Resource<GatewayQueryInput>

    suspend fun insertTransaction(transaction: TransactionEntity)

    suspend fun insertUser(userEntity: UserEntity): Long

    suspend fun deleteTransaction(transactionEntity: TransactionEntity)

    fun getUser(name: String, password: String): LiveData<UserEntity>

    fun getTransactionById(id: String): LiveData<TransactionEntity>


}