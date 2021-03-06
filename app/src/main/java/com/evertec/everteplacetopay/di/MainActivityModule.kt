package com.evertec.everteplacetopay.di.module


import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.evertec.everteplacetopay.R
import com.evertec.everteplacetopay.data.repository.Repository
import com.evertec.everteplacetopay.ui.MainViewModel
import com.evertec.everteplacetopay.ui.login.viewmodel.LoginViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityRetainedComponent::class)
class MainActivityModule {

    @Provides
    fun loginViewModelProvider(repository: Repository) = LoginViewModel(repository)

    @Provides
    fun mainViewModelsProvider(repository: Repository) = MainViewModel(repository)


}