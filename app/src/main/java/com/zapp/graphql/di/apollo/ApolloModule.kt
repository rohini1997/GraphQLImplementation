package com.zapp.graphql.di.apollo

import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApolloModule {

    @Provides
    @Singleton
    fun apolloClient():ApolloClient{
        return ApolloClient.Builder()
            .serverUrl("https://graphqlzero.almansi.me/api")
            .build()
    }

}