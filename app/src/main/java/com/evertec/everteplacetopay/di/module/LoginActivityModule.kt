package com.evertec.evertec_test.di.module

import com.evertec.everteplacetopay.data.repository.Repository
import com.evertec.everteplacetopay.ui.login.viewmodel.LoginViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class LoginActivityModule {

    @Provides
    fun loginViewModelProvider(repository: Repository)= LoginViewModel(repository)

}