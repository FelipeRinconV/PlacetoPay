package com.evertec.everteplacetopay.vo

import java.lang.Exception

/**
 * Clase para emitir los resultados de las consultas asincronas
 */
sealed class Resource<out T> {

    class Loading<out T> : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure<out T>(val exception: Exception) : Resource<T>()

}