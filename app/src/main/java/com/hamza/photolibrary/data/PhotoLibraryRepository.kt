package com.hamza.photolibrary.data

import androidx.annotation.WorkerThread
import com.hamza.photolibrary.data.api.ApiService
import com.hamza.photolibrary.data.model.Photo
import com.hamza.photolibrary.utils.StringRes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.hamza.photolibrary.R

interface PhotoLibraryRepository {
    suspend fun loadPhotos(pageNumber: Int): Flow<DataState<List<Photo>>>
}

class MainPhotoLibraryRepository @Inject constructor(
    private val stringRes: StringRes,
    private val apiService: ApiService
): PhotoLibraryRepository {

    @WorkerThread
    override suspend fun loadPhotos(
        pageNumber: Int
    ): Flow<DataState<List<Photo>>> {
        return flow {
            try {
                val photosList = apiService.loadPhotos(pageNumber)
                if (photosList != null) {
                    emit(DataState.success(photosList))
                } else {
                    emit(DataState.error<List<Photo>>(stringRes.getString(R.string.message_something_went_wrong_str)))
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
                emit(DataState.error<List<Photo>>(exception.localizedMessage))
            }
        }
    }
}