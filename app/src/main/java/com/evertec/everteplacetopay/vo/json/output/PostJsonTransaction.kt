package com.evertec.everteplacetopay.vo.json.output

import android.os.Parcelable
import com.evertec.everteplacetopay.vo.json.input.AmountInput
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


/**
 * Conjunto de data data class para enviar la informacion necesaria para generar el pago
 */
data class PostJsonTransaction(
    val auth: Auth,
    val locale: String,
    val payment: Payment,
    val ipAddress: String,
    val userAgent: String,
    val instrument: Instrument,
    val payer: Payer,
    val buyer: Buyer
)

@Parcelize
data class Auth(

    val login: String,
    val tranKey: String,
    val nonce: String,
    val seed: String
) : Parcelable

@Parcelize
data class Amount(

    val taxes: @RawValue List<Taxes>,
    val details: @RawValue List<Details>,
    val currency: String,
    val total: Double
) : Parcelable

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

@Parcelize
data class Credit(
    val code: Int,
    val type: Int,
    val groupCode: String,
    val installment: Int
) : Parcelable


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

@Parcelize
data class Payment(
    val reference: String,
    val description: String,
    val amountInput: AmountInput
) : Parcelable

@Parcelize
data class Taxes(

    val kind: String,
    val amount: Double,
    val base: Int
) : Parcelable