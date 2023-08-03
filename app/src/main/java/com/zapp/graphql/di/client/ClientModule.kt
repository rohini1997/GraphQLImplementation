package com.zapp.graphql.di.client

import com.zapp.graphql.data.client.PhotosClientImpl
import com.zapp.graphql.domain.client.PhotosClient
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)

abstract class ClientModule {

    @Binds
    @ViewModelScoped
    abstract fun bindRickAndMortyClient(photosClientImpl: PhotosClientImpl): PhotosClient
}