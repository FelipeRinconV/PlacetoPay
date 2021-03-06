package com.evertec.everteplacetopay.vo.json.processTransaction.output

data class Auth(
    val login: String,
    val nonce: String,
    val seed: String,
    val tranKey: String
)