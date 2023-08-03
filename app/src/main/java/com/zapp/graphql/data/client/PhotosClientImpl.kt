package com.zapp.graphql.data.client

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.zapp.CreatePhotoMutation
import com.zapp.DeletePhotoMutation
import com.zapp.GetPhotosQuery
import com.zapp.UpdatePhotoMutation
import com.zapp.graphql.data.dto.Photos
import com.zapp.graphql.data.mapper.toMap
import com.zapp.graphql.domain.client.PhotosClient
import javax.inject.Inject

class PhotosClientImpl
    @Inject constructor(
    private val apolloClient: ApolloClient
): PhotosClient {
    override suspend fun getAllPhotos(): List<Photos>?{
        return apolloClient
            .query(GetPhotosQuery())
            .execute()
            .data
            ?.photos
            ?.data
            ?.map {
                it!!.toMap()
            }

    }

    override suspend fun deletePhoto(photoId: String): Boolean {
        val mutation = DeletePhotoMutation(photoId)

        val response = try {
            apolloClient.mutation(mutation).execute()
        } catch (e: ApolloException) {
            return false // Return false if the mutation execution fails
        }

        return response.data?.deletePhoto ?: false
    }

    override suspend fun updatePhoto(photoId: String,newTitle:String): Boolean {
        val mutation = UpdatePhotoMutation(photoId,newTitle)

        val response = try {
            apolloClient.mutation(mutation).execute()
        } catch (e: ApolloException) {
            return false // Return false if the mutation execution fails
        }
        return response.data?.updatePhoto?.id !=null

    }

    override suspend fun createPhoto(title: String,url:String,thumbnailUrl:String): Boolean {
        val mutation = CreatePhotoMutation(title,url,thumbnailUrl)

        val response = try {
            apolloClient.mutation(mutation).execute()
        } catch (e: ApolloException) {
            return false // Return false if the mutation execution fails
        }
        return response.data?.createPhoto?.id !=null

    }


}