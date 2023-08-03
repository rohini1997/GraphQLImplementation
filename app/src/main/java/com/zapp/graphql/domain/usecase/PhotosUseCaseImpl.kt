package com.zapp.graphql.domain.usecase

import com.zapp.graphql.data.NetworkResponse
import com.zapp.graphql.data.dto.Photos
import com.zapp.graphql.domain.source.remote.RemoteSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotosUseCaseImpl @Inject constructor(private val source: RemoteSource) :
    PhotosUseCase {
    override  fun invoke(): Flow<NetworkResponse<List<Photos>>> =
        flow {
            emit(NetworkResponse.Loading)
            when (val response = source.getPhotosQuery()) {
                is NetworkResponse.Error -> emit(response)
                NetworkResponse.Loading -> Unit
                is NetworkResponse.Success -> emit(
                    NetworkResponse.Success(
                        response.result
                    )
                )
            }
        }

    override suspend fun deletePhoto(photoId: String): Flow<NetworkResponse<Boolean>> =
        flow {
            emit(NetworkResponse.Loading)
            // Perform the deletion operation using the data source (source)
            try {
                val deletionResult = source.deletePhoto(photoId)
                emit(NetworkResponse.Success(true))
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.localizedMessage ?: "Deletion failed"))
            }
        }



    override suspend fun updatePhoto(photoId: String, newTitle: String): Flow<NetworkResponse<Boolean>> =
        flow {
            emit(NetworkResponse.Loading)
            // Perform the update operation using the data source (source)
            try {
                val updateResult = source.updatePhoto(photoId, newTitle)
                    emit(NetworkResponse.Success(true))

            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.localizedMessage ?: "Update failed"))
            }
        }
    override suspend fun createPhoto(title: String, url: String,thumbnailUrl:String): Flow<NetworkResponse<Boolean>> =
        flow {
            emit(NetworkResponse.Loading)
            // Perform the update operation using the data source (source)
            try {
                val updateResult = source.createPhoto(title, url,thumbnailUrl)
                    emit(NetworkResponse.Success(true))

            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.localizedMessage ?: "Creation failed"))
            }
        }
}