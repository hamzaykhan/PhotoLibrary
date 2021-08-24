package com.hamza.photolibrary.data.api.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.hamza.photolibrary.data.model.Photo

data class SearchPhotosResponse(
    @Expose @SerializedName("total") val total: Int,
    @Expose @SerializedName("total_pages") val totalPages: Int,
    @Expose @SerializedName("results") val photosList: List<Photo>
)