package com.evertec.everteplacetopay.vo.json.output

data class Payment(
    val amount: Amount,
    val description: String,
    val reference: String
)