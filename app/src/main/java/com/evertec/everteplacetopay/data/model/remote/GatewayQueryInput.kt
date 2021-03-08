package com.evertec.everteplacetopay.data.model.remote

import com.evertec.everteplacetopay.vo.json.input.*

/**
 * Respuesta json recibida de https://dev.placetopay.com/rest/gateway/query
 */
data class GatewayQueryInput(
    val additional: Additional,
    val amount: Amount,
    val authorization: String,
    val conversion: Conversion,
    val date: String,
    val discount: Any,
    val franchise: String,
    val franchiseName: String,
    val internalReference: Int,
    val issuerName: String,
    val lastDigits: String,
    val paymentMethod: String,
    val processorFields: ProcessorFields,
    val provider: String,
    val receipt: String,
    val reference: String,
    val refunded: Boolean,
    val status: Status,
    val transactionDate: String,
    val type: String
)