package com.evertec.everteplacetopay.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.evertec.everteplacetopay.data.model.TransactionEntity
import com.evertec.everteplacetopay.data.model.UserEntity
import com.evertec.everteplacetopay.data.model.UserWithTransactionList

@Database(
    entities = [TransactionEntity::class, UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

}