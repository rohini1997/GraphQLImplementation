package com.zapp.graphql.domain.source.remote

import com.zapp.graphql.data.NetworkResponse
import com.zapp.graphql.data.dto.Photos

interface RemoteSource {
    suspend fun getPhotosQuery(): NetworkResponse<List<Photos>>
    suspend fun deletePhoto(photoId: String): NetworkResponse<Boolean>
    suspend fun updatePhoto(id: String,title: String): NetworkResponse<Boolean>
    suspend fun createPhoto(title: String, url: String,thumbnailUrl:String): NetworkResponse<Boolean>
}