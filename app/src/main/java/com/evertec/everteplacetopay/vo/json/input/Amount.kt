package com.evertec.everteplacetopay.vo.json.processTransaction.input

data class Amount(
    val currency: String,
    val details: List<Detail>,
    val taxes: List<Taxe>,
    val total: Int
)