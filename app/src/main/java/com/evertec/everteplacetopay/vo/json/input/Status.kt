package com.evertec.everteplacetopay.vo.json.processTransaction.input

data class Status(
    val date: String,
    val message: String,
    val reason: String,
    val status: String
)