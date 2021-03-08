package com.evertec.everteplacetopay.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithTransactionList(

    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "id_user",
        entityColumn = "id_creator_user"
    )
    val transactionList: List<TransactionEntity>
)

