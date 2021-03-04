import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Archivo que contiene los data class para recibir la informacion de la api proccesTransatin
 * el objeto que componen estas clases  se encuentra en  data/model/transaction
 */

data class Additional(

    @SerializedName("credit") val credit: Credit,
    @SerializedName("totalAmount") val totalAmount: Double,
    @SerializedName("interestAmount") val interestAmount: Double,
    @SerializedName("installmentAmount") val installmentAmount: Int,
    @SerializedName("iceAmount") val iceAmount: Int
)

data class AmountInput(

    @SerializedName("currency") val currency: String,
    @SerializedName("total") val total: Double
)

data class Conversion(

    @SerializedName("from") val from: From,
    @SerializedName("to") val to: To,
    @SerializedName("factor") val factor: Double
)

data class From(

    @SerializedName("currency") val currency: String,
    @SerializedName("total") val total: Double
)

data class Status(

    @SerializedName("status") val status: String,
    @SerializedName("reason") val reason: Int,
    @SerializedName("message") val message: String,
    @SerializedName("date") val date: String
)


data class To(

    @SerializedName("currency") val currency: String,
    @SerializedName("total") val total: Double
)