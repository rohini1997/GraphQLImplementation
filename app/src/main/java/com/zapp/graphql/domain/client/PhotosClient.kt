package com.zapp.graphql.domain.client

import com.zapp.graphql.data.dto.Photos

interface PhotosClient {
    suspend fun getAllPhotos():List<Photos>?
    suspend fun deletePhoto(photoId: String):Boolean
    suspend fun updatePhoto(photoId: String,newTitle: String):Boolean
    suspend fun createPhoto(title: String,url: String,thumbnailUrl:String):Boolean
}