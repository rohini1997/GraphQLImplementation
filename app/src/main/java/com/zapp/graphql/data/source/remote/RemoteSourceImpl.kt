package com.zapp.graphql.data.source.remote

import com.apollographql.apollo3.exception.ApolloException
import com.zapp.DeletePhotoMutation
import com.zapp.graphql.data.NetworkResponse
import com.zapp.graphql.data.dto.Photos
import com.zapp.graphql.domain.client.PhotosClient
import com.zapp.graphql.domain.source.remote.RemoteSource
import javax.inject.Inject

class RemoteSourceImpl @Inject constructor(private val client: PhotosClient): RemoteSource {
    override suspend fun getPhotosQuery(): NetworkResponse<List<Photos>> =
        try {
            val response = client.getAllPhotos()
            NetworkResponse.Success(response)
        } catch (e: Exception) {
            NetworkResponse.Error(e.toString())
        }

    override suspend fun deletePhoto(photoId: String): NetworkResponse<Boolean> =
        try {
            // Call the deletePhoto method on the client with the given photoId
            val deletionResult = client.deletePhoto(photoId)
            NetworkResponse.Success(deletionResult)
        } catch (e: Exception) {
            NetworkResponse.Error(e.toString())
        }

    override suspend fun updatePhoto(photoId: String, newTitle: String): NetworkResponse<Boolean> =
        try {
            // Perform the edit operation using the client or data source
            val editResult = client.updatePhoto(photoId, newTitle)

            // Return success or error response based on the editResult
            if (editResult) {
                NetworkResponse.Success(true)
            } else {
                NetworkResponse.Error("Edit failed")
            }
        } catch (e: Exception) {
            NetworkResponse.Error(e.localizedMessage ?: "Edit failed")
        }

 override suspend fun createPhoto(title: String, url: String,thumbnailUrl:String): NetworkResponse<Boolean> =
        try {
            // Perform the edit operation using the client or data source
            val editResult = client.createPhoto(title, url,thumbnailUrl)

            // Return success or error response based on the editResult
            if (editResult) {
                NetworkResponse.Success(true)
            } else {
                NetworkResponse.Error("Edit failed")
            }
        } catch (e: Exception) {
            NetworkResponse.Error(e.localizedMessage ?: "Edit failed")
        }


}

