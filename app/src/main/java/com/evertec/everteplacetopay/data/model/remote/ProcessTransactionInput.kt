package com.evertec.everteplacetopay.data.model

import android.os.Parcelable
import com.evertec.everteplacetopay.vo.json.input.*
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

/**
 * Respuesta recibida al consulta la api https://dev.placetopay.com/rest/gateway/process
 */
@Parcelize
data class ProcessTransactionInput(
    val additional: @RawValue Additional,
    val amount: @RawValue Amount,
    val authorization: String,
    val conversion: @RawValue Conversion,
    val date: String,
    val discount: @RawValue Any,
    val franchise: String,
    val franchiseName: String,
    val internalReference: Int,
    val issuerName: String,
    val lastDigits: String,
    val paymentMethod: String,
    val processorFields: @RawValue ProcessorFields,
    val provider: String,
    val receipt: String,
    val reference: String,
    val refunded: Boolean,
    val status: @RawValue Status,
    val transactionDate: String,
    val type: String
) : Parcelable