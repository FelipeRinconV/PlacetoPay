package com.evertec.everteplacetopay.vo.json.input

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Conversion(
    val factor: Double,
    val from: @RawValue From,
    val to: @RawValue To
) : Parcelable