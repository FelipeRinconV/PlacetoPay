package com.evertec.everteplacetopay.vo.json.input

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Status(
    val date: String,
    val message: String,
    val reason: String,
    val status: String
) : Parcelable