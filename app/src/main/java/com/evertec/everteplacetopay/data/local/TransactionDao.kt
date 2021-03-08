package com.evertec.everteplacetopay.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.evertec.everteplacetopay.data.model.TransactionEntity
import com.evertec.everteplacetopay.data.model.UserEntity
import com.evertec.everteplacetopay.data.model.UserWithTransactionList

@Dao
interface TransactionDao {

    @Transaction
    @Query("SELECT TransactionEntity.id_transaction,TransactionEntity.id_creator_user,TransactionEntity.card_number,TransactionEntity.date,TransactionEntity.reference,TransactionEntity.state,TransactionEntity.value FROM UserEntity INNER JOIN TransactionEntity ON  TransactionEntity.id_creator_user =  UserEntity.id_user WHERE UserEntity.id_user =:idUser")
    fun getAllTransactions(idUser: Long): LiveData<List<TransactionEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: UserEntity): Long

    @Query("SELECT * FROM UserEntity WHERE name =:name AND password =:password  ")
    fun getUser(name: String, password: String): LiveData<UserEntity>

    @Query("SELECT * FROM UserEntity")
    fun getAllUserWithTransactionList(): LiveData<List<UserWithTransactionList>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionEntity)

    @Delete
    suspend fun deleteTransaction(transaction: TransactionEntity);

    @Query("SELECT TransactionEntity.id_transaction,TransactionEntity.id_creator_user,TransactionEntity.card_number,TransactionEntity.date,TransactionEntity.reference,TransactionEntity.state,TransactionEntity.value  FROM UserEntity INNER JOIN TransactionEntity ON  TransactionEntity.id_creator_user =  UserEntity.id_user WHERE TransactionEntity.id_transaction =:id")
    fun getTransactionById(id: String): LiveData<TransactionEntity>

}