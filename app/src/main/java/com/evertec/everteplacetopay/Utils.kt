package com.evertec.everteplacetopay

import android.os.Build
import androidx.annotation.RequiresApi
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

fun getNonce ():String {
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


