package com.evertec.everteplacetopay.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.evertec.everteplacetopay.data.model.TransactionEntity

@Database(entities = [TransactionEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

}