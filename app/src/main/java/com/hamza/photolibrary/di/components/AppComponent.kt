package com.hamza.photolibrary.di.components

import android.app.Application
import com.hamza.photolibrary.PhotoLibraryApp
import com.hamza.photolibrary.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * The Dagger Component which provides the modules used throughout the app
 * @author Hamza Khan
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelsFactoryModule::class,
        ViewModelModule::class,
        NetworkApiModule::class,
        RepositoryModule::class
    ]
)

interface AppComponent : AndroidInjector<PhotoLibraryApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(app: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: PhotoLibraryApp)
}