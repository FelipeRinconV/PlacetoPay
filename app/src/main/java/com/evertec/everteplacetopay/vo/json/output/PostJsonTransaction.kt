package com.evertec.everteplacetopay.vo.json.output

import com.evertec.everteplacetopay.vo.json.input.AmountInput


/**
 * Conjunto de data data class para enviar la informacion necesaria para generar el pago
 */
data class PostJsonTransaction(

   // val auth: Auth,
    val locale: String,
    //  val payment: Payment,
    val ipAddress: String,
    val userAgent: String,
    //   val instrument: Instrument,
    //  val payer: Payer,
    //  val buyer: Buyer
)

data class Auth(

    val login: String,
    val tranKey: String,
    val nonce: String,
    val seed: String
)

data class Amount(

    val taxes: List<Taxes>,
    val details: List<Details>,
    val currency: String,
    val total: Double
)

data class Buyer(

    val document: Int,
    val documentType: String,
    val name: String,
    val surname: String,
    val email: String,
    val mobile: Int
)

data class Card(

    val number: Int,
    val expirationMonth: Int,
    val expirationYear: Int,
    val cvv: Int
)

data class Credit(

    val code: Int,
    val type: Int,
    val groupCode: String,
    val installment: Int
)


data class Details(

    val kind: String,
    val amount: Int
)

data class Instrument(

    val card: Card,
    val credit: Credit,
    val otp: String
)

data class Payer(

    val document: Int,
    val documentType: String,
    val name: String,
    val surname: String,
    val email: String,
    val mobile: Int
)

data class Payment(

    val reference: String,
    val description: String,
    val amountInput: AmountInput
)

data class Taxes(

    val kind: String,
    val amount: Double,
    val base: Int
)