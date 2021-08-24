package com.hamza.photolibrary.dagger.modules

import android.app.Application
import com.hamza.photolibrary.data.api.ApiService
import com.hamza.photolibrary.data.PhotoLibraryRepository
import com.hamza.photolibrary.data.MainPhotoLibraryRepository
import com.hamza.photolibrary.utils.StringRes
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ReposModule {
    @Singleton
    @Provides
    fun provideStringResources(app: Application): StringRes {
        return StringRes(app)
    }

    @Singleton
    @Provides
    fun provideImagineRepository(stringRes: StringRes, apiService: ApiService): PhotoLibraryRepository {
        return MainPhotoLibraryRepository(stringRes, apiService)
    }
}