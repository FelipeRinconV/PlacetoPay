package com.evertec.everteplacetopay.data.model

import Additional
import Amount
import AmountInput
import Conversion
import Status
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


/**
 * Respuesta recibida al consulta la api  @POST("gateway/process")
 */

data class Transaction(
    var status: Status,
    val internalReference: Int,
    val reference: String,
    val paymentMethod: String,
    val franchise: String,
    val franchiseName: String,
    val issuerName: String,
    val amountInput: AmountInput,
    val conversion: Conversion,
    val authorization: Int,
    val receipt: Int,
    val type: String,
    val refunded: Boolean,
    val lastDigits: Int,
    val provider: String,
    val discount: String,
    val processorFields: List<String>,
    val additional: Additional
)


/**
 * Respuesta recibida al consulta la api  @POST("gateway/query")
 */
data class InfoTransaction(

    val status: Status,
    val internalReference: Int,
    val reference: String,
    val paymentMethod: String,
    val franchise: String,
    val franchiseName: String,
    val issuerName: String,
    val amount: Amount,
    val conversion: Conversion,
    val authorization: Int,
    val receipt: Int,
    val type: String,
    val refunded: Boolean,
    val lastDigits: Int,
    val provider: String,
    val discount: String,
    val processorFields: List<String>,
    val additional: Additional
)


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
    val value: Double,
    @ColumnInfo(name = "date")
    val date: Date
)