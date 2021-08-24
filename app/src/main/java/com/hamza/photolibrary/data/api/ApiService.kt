package com.hamza.photolibrary.data.api

import com.hamza.photolibrary.data.model.Photo
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("photos")
    suspend fun loadPhotos(
        @Query("page") page: Int = 1,
        @Query("per_page") numOfPhotos: Int = 20,
        @Query("order_by") orderBy: String = "popular"
    ): List<Photo>
}