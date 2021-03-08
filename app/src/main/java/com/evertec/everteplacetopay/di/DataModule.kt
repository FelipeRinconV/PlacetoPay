package com.evertec.everteplacetopay.di

import android.content.Context
import android.icu.util.TimeUnit
import androidx.room.Room
import com.evertec.everteplacetopay.data.local.AppDatabase
import com.evertec.everteplacetopay.data.rest.WebService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun retrofitProviders(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(6, java.util.concurrent.TimeUnit.MINUTES)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://dev.placetopay.com/rest/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Provides
    @Singleton
    fun providerWebService(retrofit: Retrofit): WebService = retrofit.create(WebService::class.java)

    @Provides
    @Singleton
    fun dataBaseProvider(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "evertec_test"
    )
        .build()

    @Provides
    @Singleton
    fun transactionDaoProvider(appDatabase: AppDatabase) = appDatabase.transactionDao()


}