package com.evertec.everteplacetopay.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evertec.everteplacetopay.data.model.TransactionEntity

@Dao
interface TransactionDao {

    @Query("SELECT * FROM TransactionEntity")
    suspend fun getAllTransactions(): List<TransactionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionEntity)

}