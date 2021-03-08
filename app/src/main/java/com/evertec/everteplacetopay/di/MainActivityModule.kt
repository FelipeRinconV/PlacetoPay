package com.evertec.everteplacetopay.di



import com.evertec.everteplacetopay.data.repository.Repository
import com.evertec.everteplacetopay.ui.MainViewModel
import com.evertec.everteplacetopay.ui.login.viewmodel.LoginViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Singleton


@Module
@InstallIn(ActivityRetainedComponent::class)
class MainActivityModule {


    @Provides
    fun mainViewModelsProvider(repository: Repository) =
        MainViewModel(repository)

    @Provides
    fun loginViewModelProvider(repository: Repository) = LoginViewModel(repository)
}