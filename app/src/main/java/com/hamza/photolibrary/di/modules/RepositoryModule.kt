package com.hamza.photolibrary.di.modules

import android.app.Application
import com.hamza.photolibrary.data.remote.UnsplashApiService
import com.hamza.photolibrary.data.repository.PhotoLibraryRepository
import com.hamza.photolibrary.data.repository.PhotoLibraryRepositoryImpl
import com.hamza.photolibrary.utils.StringUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideStringUtils(app: Application): StringUtils {
        return StringUtils(app)
    }

    @Singleton
    @Provides
    fun provideImagineRepository(stringUtils: StringUtils, apiService: UnsplashApiService): PhotoLibraryRepository {
        return PhotoLibraryRepositoryImpl(stringUtils, apiService)
    }
}