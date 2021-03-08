package com.evertec.everteplacetopay.vo.json.input

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProcessorFields(
    val b24: String,
    val id: String
):Parcelable