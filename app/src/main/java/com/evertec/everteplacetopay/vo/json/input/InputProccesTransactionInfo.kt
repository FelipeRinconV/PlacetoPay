
package com.evertec.everteplacetopay.vo.json.input

import com.evertec.everteplacetopay.vo.json.output.Credit


/**
 * Archivo que contiene los data class para recibir la informacion de la api proccesTransatin
 * el objeto que componen estas clases  se encuentra en  data/model/transaction
 */

data class Additional(

    val credit: Credit,
    val totalAmount: Double,
    val interestAmount: Double,
    val installmentAmount: Int,
    val iceAmount: Int
)

data class AmountInput(

    val currency: String,
    val total: Double
)

data class Conversion(

    val from: From,
    val to: To,
    val factor: Double
)

data class From(

    val currency: String,
    val total: Double
)

data class Status(

    val status: String,
    val reason: Int,
    val message: String,
    val date: String
)


data class To(
    val currency: String,
    val total: Double
)