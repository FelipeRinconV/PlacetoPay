package com.evertec.everteplacetopay.vo.json.output

data class Auth(
    val login: String,
    val nonce: String,
    val seed: String,
    val tranKey: String
)