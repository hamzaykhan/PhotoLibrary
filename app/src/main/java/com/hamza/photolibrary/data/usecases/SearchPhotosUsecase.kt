package com.hamza.photolibrary.data.usecases

import com.hamza.photolibrary.data.repository.PhotoLibraryRepository
import com.hamza.photolibrary.utils.AppConstants
import javax.inject.Inject

/**
 * A use-case to search photos from Unsplash API.
 * @author Wajahat Karim
 */
class SearchPhotosUsecase @Inject constructor(private val repository: PhotoLibraryRepository) {
    suspend operator fun invoke(
        query: String,
        pageNum: Int = 1,
        pageSize: Int = AppConstants.API.PHOTOS_PER_PAGE
    ) = repository.searchPhotos(
        query = query,
        pageNumber = pageNum,
        pageSize = pageSize
    )
}