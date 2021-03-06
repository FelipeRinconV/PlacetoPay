package com.evertec.everteplacetopay.vo.json.processTransaction.output

data class Payment(
    val amount: Amount,
    val description: String,
    val reference: String
)