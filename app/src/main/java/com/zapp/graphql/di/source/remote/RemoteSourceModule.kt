package com.zapp.graphql.di.source.remote

import com.zapp.graphql.data.source.remote.RemoteSourceImpl
import com.zapp.graphql.domain.source.remote.RemoteSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)

abstract class RemoteSourceModule {

    @Binds
    @ViewModelScoped
    abstract fun bindPhotosRemoteSource(remoteSourceImpl: RemoteSourceImpl): RemoteSource
}