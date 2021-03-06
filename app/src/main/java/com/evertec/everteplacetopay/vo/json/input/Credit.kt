package com.evertec.everteplacetopay.vo.json.input

data class Credit(
    val code: Int,
    val groupCode: String,
    val installments: Int,
    val type: String
)