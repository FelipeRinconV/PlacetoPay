package com.evertec.everteplacetopay.vo.json.input

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Additional(
    val batch: @RawValue Any,
    val bin: String,
    val credit: @RawValue Credit,
    val expiration: String,
    val iceAmount: Double,
    val installmentAmount: Int,
    val interestAmount: Int,
    val line: @RawValue Any,
    val merchantCode: String,
    val terminalNumber: String,
    val totalAmount: Int
) : Parcelable