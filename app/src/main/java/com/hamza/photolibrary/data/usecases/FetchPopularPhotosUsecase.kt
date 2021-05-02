package com.hamza.photolibrary.data.usecases

import com.hamza.photolibrary.data.repository.PhotoLibraryRepository
import com.hamza.photolibrary.utils.AppConstants
import javax.inject.Inject

class FetchPopularPhotosUsecase @Inject constructor(private val repository: PhotoLibraryRepository) {
    suspend operator fun invoke(
        pageNum: Int = 1,
        pageSize: Int = AppConstants.API.PHOTOS_PER_PAGE,
        orderBy: String = "popular"
    ) = repository.loadPhotos(
        pageNumber = pageNum,
        pageSize = pageSize,
        orderBy = orderBy
    )
}