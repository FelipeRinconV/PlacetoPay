package com.evertec.everteplacetopay.di

import com.evertec.everteplacetopay.data.repository.RepoImplementation
import com.evertec.everteplacetopay.data.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent


@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityRetainedModule {

    @Binds
    abstract fun dataSourceProvider(impl: RepoImplementation): Repository
}