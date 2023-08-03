package com.zapp.graphql.di.usecase

import com.zapp.graphql.domain.usecase.PhotosUseCase
import com.zapp.graphql.domain.usecase.PhotosUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)

abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun bindPhotosUseCase(rickAndMortyUseCaseImpl: PhotosUseCaseImpl): PhotosUseCase
}