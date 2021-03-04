package com.evertec.everteplacetopay.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.evertec.everteplacetopay.data.model.TransactionEntity

@Database(entities = [TransactionEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun coktailsDao(): CocktailsDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "table_transaction"
            ).build()

            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }

    }

}