import com.google.gson.annotations.SerializedName


/**
 * Conjunto de data class para enviar la informacion necesaria para generar el pago
 */
data class PostJsonTransaction(

    @SerializedName("auth") val auth: Auth,
    @SerializedName("locale") val locale: String,
    @SerializedName("payment") val payment: Payment,
    @SerializedName("ipAddress") val ipAddress: String,
    @SerializedName("userAgent") val userAgent: String,
    @SerializedName("instrument") val instrument: Instrument,
    @SerializedName("payer") val payer: Payer,
    @SerializedName("buyer") val buyer: Buyer
)

data class Auth(

    @SerializedName("login") val login: String,
    @SerializedName("tranKey") val tranKey: String,
    @SerializedName("nonce") val nonce: String,
    @SerializedName("seed") val seed: String
)

data class Amount(

    @SerializedName("taxes") val taxes: List<Taxes>,
    @SerializedName("details") val details: List<Details>,
    @SerializedName("currency") val currency: String,
    @SerializedName("total") val total: Double
)

data class Buyer(

    @SerializedName("document") val document: Int,
    @SerializedName("documentType") val documentType: String,
    @SerializedName("name") val name: String,
    @SerializedName("surname") val surname: String,
    @SerializedName("email") val email: String,
    @SerializedName("mobile") val mobile: Int
)

data class Card(

    @SerializedName("number") val number: Int,
    @SerializedName("expirationMonth") val expirationMonth: Int,
    @SerializedName("expirationYear") val expirationYear: Int,
    @SerializedName("cvv") val cvv: Int
)

data class Credit(

    @SerializedName("code") val code: Int,
    @SerializedName("type") val type: Int,
    @SerializedName("groupCode") val groupCode: String,
    @SerializedName("installment") val installment: Int
)


data class Details(

    @SerializedName("kind") val kind: String,
    @SerializedName("amount") val amount: Int
)

data class Instrument(

    @SerializedName("card") val card: Card,
    @SerializedName("credit") val credit: Credit,
    @SerializedName("otp") val otp: String
)

data class Payer(

    @SerializedName("document") val document: Int,
    @SerializedName("documentType") val documentType: String,
    @SerializedName("name") val name: String,
    @SerializedName("surname") val surname: String,
    @SerializedName("email") val email: String,
    @SerializedName("mobile") val mobile: Int
)

data class Payment(

    @SerializedName("reference") val reference: String,
    @SerializedName("description") val description: String,
    @SerializedName("amount") val amountInput: AmountInput
)

data class Taxes(

    @SerializedName("kind") val kind: String,
    @SerializedName("amount") val amount: Double,
    @SerializedName("base") val base: Int
)