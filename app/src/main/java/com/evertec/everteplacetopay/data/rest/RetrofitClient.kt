package com.evertec.everteplacetopay.data.rest

import com.evertec.evertec_test.data.rest.WebService
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * https://docs.google.com/document/d/1wk2d5DzFjVfTk-JLOJhHFwHTbzuwc6HRi-liYWjeRPw
 */
object RetrofitClient {

    val webService by lazy {

        Retrofit.Builder()
            .baseUrl("https://dev.placetopay.com/rest/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)

    }


}