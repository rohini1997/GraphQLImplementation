package com.zapp.graphql.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zapp.graphql.R
import com.zapp.graphql.data.NetworkResponse
import com.zapp.graphql.domain.usecase.PhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val photoUseCase: PhotosUseCase
) : ViewModel() {

    private val _photo = MutableLiveData<HomeUiState>()
    val photo: LiveData<HomeUiState> = _photo
   private val _photoDeleted = MutableLiveData<DeleteUiState>()
    val photoDeleted: LiveData<DeleteUiState> = _photoDeleted
   private val _photoUpdated = MutableLiveData<DeleteUiState>()
    val photoUpdated: LiveData<DeleteUiState> = _photoUpdated
private val _photoCreated = MutableLiveData<DeleteUiState>()
    val photoCreated: LiveData<DeleteUiState> = _photoCreated


    init {
        getAllPhotos()
    }

    fun getAllPhotos() {
            photoUseCase().onEach {
                when (it) {
                    is NetworkResponse.Error -> {
                        _photo.postValue(HomeUiState.Error(R.string.error))
                    }
                    NetworkResponse.Loading -> {
                        _photo.postValue(HomeUiState.Loading)
                    }
                    is NetworkResponse.Success -> {
                        _photo.postValue(HomeUiState.Success(it.result))
                    }
                }

            }.launchIn(viewModelScope)



    }
     fun deletePhoto(photoId:String) {
        viewModelScope.launch {
            photoUseCase.deletePhoto(photoId).onEach {
                when (it) {
                    is NetworkResponse.Error -> {
                        _photoDeleted.postValue(DeleteUiState.Error(R.string.error))
                    }
                    NetworkResponse.Loading -> {
                        _photoDeleted.postValue(DeleteUiState.Loading)
                    }
                    is NetworkResponse.Success -> {
                        _photoDeleted.postValue(DeleteUiState.Deleted(true))
                    }
                }

            }.launchIn(viewModelScope)

    }}
 fun updatePhoto(photoId:String, newTitle:String) {
        viewModelScope.launch {
            photoUseCase.updatePhoto(photoId,newTitle).onEach {
                when (it) {
                    is NetworkResponse.Error -> {
                        _photoUpdated.postValue(DeleteUiState.Error(R.string.error))
                    }
                    NetworkResponse.Loading -> {
                        _photoUpdated.postValue(DeleteUiState.Loading)
                    }
                    is NetworkResponse.Success -> {
                        _photoUpdated.postValue(DeleteUiState.Deleted(true))
                    }
                }

            }.launchIn(viewModelScope)

    }}

    fun createPhoto(title:String, url:String,thumbnailUrl:String) {
        viewModelScope.launch {
            photoUseCase.createPhoto(title,url,thumbnailUrl).onEach {
                when (it) {
                    is NetworkResponse.Error -> {
                        _photoCreated.postValue(DeleteUiState.Error(R.string.error))
                    }
                    NetworkResponse.Loading -> {
                        _photoCreated.postValue(DeleteUiState.Loading)
                    }
                    is NetworkResponse.Success -> {
                        _photoCreated.postValue(DeleteUiState.Deleted(true))
                    }
                }

            }.launchIn(viewModelScope)

        }}


}