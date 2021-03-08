package com.evertec.everteplacetopay.data.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

/**
 * Entidad para guardar en room la informacion que se le va mostrar al usuario de sus transacciones
 */
@Entity(
    tableName = "TransactionEntity",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id_user"],
            childColumns = ["id_creator_user"],
            onDelete = CASCADE
        )], indices = [Index(value = arrayOf("id_creator_user"))]
)
data class TransactionEntity(
    @PrimaryKey()
    @ColumnInfo(name = "id_transaction")
    val internalReference: Long,
    @ColumnInfo(name = "id_creator_user")
    val userCreatorId: Long,
    @ColumnInfo(name = "reference")
    val reference: String,
    @ColumnInfo(name = "card_number")
    val cardNumber: String = "",
    @ColumnInfo(name = "state")
    var state: String = "",
    @ColumnInfo(name = "value")
    val value: Double,
    @ColumnInfo(name = "date")
    val date: String
)

