package com.zapp.graphql.ui.home

import androidx.annotation.StringRes
import com.zapp.graphql.data.dto.Photos

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val data: List<Photos>?) : HomeUiState()
    data class Error(@StringRes val message: Int) : HomeUiState()
//    data class Deleted(val deleted: Boolean) : HomeUiState()
}