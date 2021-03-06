package com.evertec.everteplacetopay.di.module

import android.content.Context
import androidx.room.Room
import com.evertec.everteplacetopay.data.local.AppDatabase
import com.evertec.everteplacetopay.data.local.TransactionDao
import com.evertec.everteplacetopay.data.repository.DataSource
import com.evertec.everteplacetopay.data.repository.RepoImplementation
import com.evertec.everteplacetopay.data.repository.Repository
import com.evertec.everteplacetopay.data.rest.RetrofitClient
import com.evertec.everteplacetopay.data.rest.WebService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun retrofitProviders(): Retrofit = Retrofit.Builder()
        .baseUrl("https://dev.placetopay.com/rest/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    @Provides
    @Singleton
    fun providerWebService(retrofit: Retrofit): WebService = retrofit.create(WebService::class.java)

    @Provides
    @Singleton
    fun dataBaseProvider(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "table_transaction")
        .build()

    @Provides
    @Singleton
    fun transactionDaoProvider(appDatabase: AppDatabase) = appDatabase.transactionDao()


}