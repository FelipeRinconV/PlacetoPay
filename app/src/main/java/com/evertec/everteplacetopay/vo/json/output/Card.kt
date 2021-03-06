package com.evertec.everteplacetopay.vo.json.output

data class Card(
    val cvv: String,
    val expirationMonth: String,
    val expirationYear: String,
    val number: String
)