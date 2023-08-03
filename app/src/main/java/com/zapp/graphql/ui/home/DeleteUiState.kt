package com.zapp.graphql.ui.home

import androidx.annotation.StringRes
import com.zapp.graphql.data.dto.Photos

sealed class DeleteUiState {
    object Loading : DeleteUiState()
//    data class Success(val data: List<Photos>?) : DeleteUiState()
    data class Error(@StringRes val message: Int) : DeleteUiState()
    data class Deleted(val deleted: Boolean) : DeleteUiState()
}