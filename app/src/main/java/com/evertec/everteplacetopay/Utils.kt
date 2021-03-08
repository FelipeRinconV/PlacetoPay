package com.evertec.everteplacetopay

import android.app.Activity
import android.content.Context
import android.content.Context.WIFI_SERVICE
import android.content.SharedPreferences
import android.net.wifi.WifiManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.evertec.everteplacetopay.data.DataController
import com.evertec.everteplacetopay.data.model.UserEntity
import com.evertec.everteplacetopay.vo.json.output.Auth
import com.google.gson.Gson
import java.math.BigInteger
import java.security.MessageDigest
import java.security.SecureRandom
import java.text.SimpleDateFormat
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
fun base64(input: ByteArray?): String {
    return String(Base64.getEncoder().encode(input))
}

fun sha256(input: String): ByteArray {
    val mDigest: MessageDigest = MessageDigest.getInstance("SHA-256")
    return mDigest.digest(input.toByteArray())
}

fun ByteArray.toHexString(): String {
    return this.joinToString("") {
        java.lang.String.format("%02x", it)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun getNonceBase64(nonce: String): String {
    return base64(nonce.toByteArray())
}

fun getNonce(): String {
    return BigInteger(130, SecureRandom()).toString(16)
}

fun getSeed(): String {
    val now = Date()
    val isoDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
    return isoDate.format(now)
}


@RequiresApi(Build.VERSION_CODES.O)
fun getDigits(encoded: Boolean, tranKey: String, nonce: String, seed: String): String {
    val digest: ByteArray = sha256(nonce + seed + tranKey)
    return base64(digest)
}

fun getLocalIpAddress(context: Context): String? {
    try {
        val wifiManager: WifiManager = context?.getSystemService(WIFI_SERVICE) as WifiManager
        return ipToString(wifiManager.connectionInfo.ipAddress)
    } catch (ex: Exception) {
        Log.e("IP Address", ex.toString())
    }

    return null
}

fun ipToString(i: Int): String {
    return (i and 0xFF).toString() + "." +
            (i shr 8 and 0xFF) + "." +
            (i shr 16 and 0xFF) + "." +
            (i shr 24 and 0xFF)

}

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    requireContext().showToast(message, duration)
}


@RequiresApi(Build.VERSION_CODES.O)
fun generateAuth(): Auth {
    val login = "6dd490faf9cb87a9862245da41170ff2"
    val passwordKey = "024h1IlD"
    val nonce = getNonce()
    val nonceBase64 = getNonceBase64(nonce)
    val seedMethod = getSeed()
    val trankey = getDigits(false, passwordKey, nonce, seedMethod)

    return Auth(login, nonceBase64, seedMethod, trankey)
}


fun saveIdUser(context: Context, id: Long) {
    val sharedPref = context.getSharedPreferences(Const.SHARED.name, Context.MODE_PRIVATE)
}


fun saveUserSfp(activity: Activity, user: UserEntity) {
    val preferences: SharedPreferences =
        activity.getSharedPreferences(Const.SHARED.name, Context.MODE_PRIVATE)
    val editor = preferences.edit()
    val jsoUser: String = Gson().toJson(user)
    editor.putString(Const.USER_DATA.name, jsoUser)
    editor.commit()
}

fun getUserSfp(activity: Activity): UserEntity? {
    val json: String? = activity.getSharedPreferences(Const.SHARED.name, Context.MODE_PRIVATE)
        .getString(Const.USER_DATA.name, null)

    return if (json != null) {
        if (!json.isEmpty()) {
            Gson().fromJson(json, UserEntity::class.java)
        } else {
            null
        }

    } else {
        null
    }
}


fun singUpUser(activity: Activity) {
    DataController.cleanData()
    val preferences: SharedPreferences =
        activity.getSharedPreferences(Const.SHARED.name, Context.MODE_PRIVATE)
    val editor = preferences.edit()
    editor.putString(Const.USER_DATA.name, "")
    editor.commit()

}

fun getState(state: String): String {

    return when (state) {

        "APPROVED" -> "APROVADA"

        "REJECTED" -> "RECHAZADA"

        "PENDING" -> "PENDIENTE"

        else -> "DESCONOCIDO"
    }

}



