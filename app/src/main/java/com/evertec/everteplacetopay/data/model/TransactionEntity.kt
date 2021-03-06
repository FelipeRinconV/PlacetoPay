package com.evertec.everteplacetopay.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Entidad para guardar en room la informacion que se le va mostrar al usuario de sus transacciones
 */
@Entity
data class TransactionEntity(
    @PrimaryKey()
    @ColumnInfo(name = "id")
    val internalReference: Int,
    @ColumnInfo(name = "reference")
    val reference: String,
    @ColumnInfo(name = "card_number")
    val cardNumber: String = "",
    @ColumnInfo(name = "state")
    val state: String = "",
    @ColumnInfo(name = "value")
    val value: Double
)