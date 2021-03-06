package com.evertec.everteplacetopay.vo.json.input

import com.evertec.everteplacetopay.vo.json.processTransaction.input.From

data class Conversion(
    val factor: Int,
    val from: From,
    val to: To
)