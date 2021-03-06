package com.evertec.everteplacetopay.vo.json.output

data class proccessTransaction(
    val auth: Auth,
    val instrument: Instrument,
    val ipAddress: String,
    val locale: String,
    val payer: Payer,
    val payment: Payment,
    val userAgent: String
)