package com.evertec.everteplacetopay.vo.json.input

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Credit(
    val code: Int,
    val groupCode: String,
    val installments: Double,
    val type: String
) : Parcelable