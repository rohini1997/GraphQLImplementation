package com.zapp.graphql.data.mapper

import com.zapp.GetPhotosQuery
import com.zapp.graphql.data.dto.Photos

fun GetPhotosQuery.Data1.toMap():Photos{
    return Photos(
        id = id?:"",
        title = title?:"",
        url =url?:"",
        )
}