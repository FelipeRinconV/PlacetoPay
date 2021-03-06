package com.evertec.everteplacetopay.vo.json.processTransaction.input

import com.evertec.everteplacetopay.vo.json.input.Credit

data class Additional(
    val batch: Any,
    val bin: String,
    val credit: Credit,
    val expiration: String,
    val iceAmount: Double,
    val installmentAmount: Int,
    val interestAmount: Int,
    val line: Any,
    val merchantCode: String,
    val terminalNumber: String,
    val totalAmount: Int
)