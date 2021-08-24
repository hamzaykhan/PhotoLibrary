package com.hamza.photolibrary.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo (
    @Expose val id: String,
    @Expose val created_at: String,
    @Expose val color: String,
    @Expose val description: String,
    @Expose val urls: PhotoUrls,
    @Expose val user: User
): Parcelable