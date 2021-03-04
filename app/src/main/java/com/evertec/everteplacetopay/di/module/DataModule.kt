package com.evertec.evertec_test.di.module

import android.content.Context
import com.evertec.everteplacetopay.data.local.AppDatabase
import com.evertec.everteplacetopay.data.repository.DataSource
import com.evertec.everteplacetopay.data.repository.RepoImplementation
import com.evertec.everteplacetopay.data.repository.Repository
import com.evertec.everteplacetopay.data.rest.RetrofitClient
import com.evertec.evertec_test.data.rest.WebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun getWebServiceRetrofit(): WebService {
        return RetrofitClient.webService
    }

    @Provides
    @Singleton
    //TODO buscar como se puede injectar el modulo
    fun dataBaseProvider(context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun dataSourceProvider(database: AppDatabase, webService: WebService): DataSource {
        return DataSource(database,webService)
    }

    @Provides
    fun repositoryProviders(dataSource: DataSource): Repository {
        return RepoImplementation(dataSource)
    }


}