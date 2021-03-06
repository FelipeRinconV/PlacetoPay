package com.evertec.everteplacetopay.vo.json.input

import android.os.Parcelable
import com.evertec.everteplacetopay.vo.json.output.Credit
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


/**
 * Archivo que contiene los data class para recibir la informacion de la api proccesTransatin
 * el objeto que componen estas clases  se encuentra en  data/model/transaction
 */

@Parcelize
data class Additional(

    val credit: @RawValue Credit,
    val totalAmount: Double,
    val interestAmount: Double,
    val installmentAmount: Int,
    val iceAmount: Int
) : Parcelable

@Parcelize
data class AmountInput(
    val currency: String,
    val total: Double
) : Parcelable

@Parcelize
data class Conversion(
    val from: @RawValue From,
    val to: @RawValue To,
    val factor: Double
) : Parcelable

@Parcelize
data class From(
    val currency: String,
    val total: Double
) : Parcelable

@Parcelize
data class Status(

    val status: String,
    val reason: Int,
    val message: String,
    val date: String
) : Parcelable

@Parcelize
data class To(
    val currency: String,
    val total: Double
) : Parcelable