package com.evertec.everteplacetopay.vo.json.output

data class Payer(
    val document: String,
    val documentType: String,
    val email: String,
    val mobile: String,
    val name: String,
    val surname: String
)