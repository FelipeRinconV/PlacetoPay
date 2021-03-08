package com.evertec.everteplacetopay.di

import android.content.Context
import androidx.room.Room
import com.evertec.everteplacetopay.data.local.AppDatabase
import com.evertec.everteplacetopay.data.repository.Repository
import com.evertec.everteplacetopay.data.rest.WebService
import com.evertec.everteplacetopay.ui.login.viewmodel.LoginViewModel
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
import javax.inject.Scope
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun retrofitProviders(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

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


    @Provides
    @Singleton
    fun loginViewModelProvider(repository: Repository) = LoginViewModel(repository)
}