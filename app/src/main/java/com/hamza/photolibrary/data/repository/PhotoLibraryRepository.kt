package com.hamza.photolibrary.data.repository

import com.hamza.photolibrary.data.DataState
import com.hamza.photolibrary.model.PhotoModel
import kotlinx.coroutines.flow.Flow


interface PhotoLibraryRepository {
    suspend fun loadPhotos(pageNumber: Int, pageSize: Int, orderBy: String): Flow<DataState<List<PhotoModel>>>
    suspend fun searchPhotos(query: String, pageNumber: Int, pageSize: Int): Flow<DataState<List<PhotoModel>>>
}