package com.evertec.everteplacetopay.vo.json.input

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Detail(
    val amount: Double,
    val kind: String
) : Parcelable