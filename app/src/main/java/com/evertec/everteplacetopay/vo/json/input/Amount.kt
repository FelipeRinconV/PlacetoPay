package com.evertec.everteplacetopay.vo.json.input

import android.os.Parcelable
import com.evertec.everteplacetopay.vo.json.processTransaction.input.Taxe
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Amount(
    val currency: String,
    val details: @RawValue List<Detail>,
    //val taxes: @RawValue List<Taxe>,
    val total: Int
) : Parcelable