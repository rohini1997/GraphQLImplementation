package com.zapp.graphql.domain.usecase
import com.zapp.graphql.data.NetworkResponse
import com.zapp.graphql.data.dto.Photos
import kotlinx.coroutines.flow.Flow


interface PhotosUseCase {
     operator fun invoke(): Flow<NetworkResponse<List<Photos>>>

     // Delete a specific photo
     suspend fun deletePhoto(photoId: String): Flow<NetworkResponse<Boolean>>

     // Edit a specific photo
     suspend fun updatePhoto(photoId: String, newTitle:String): Flow<NetworkResponse<Boolean>>

     // Create a new photo
     suspend fun createPhoto(title: String, url:String,thumbnailUrl:String): Flow<NetworkResponse<Boolean>>



}