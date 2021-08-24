package com.hamza.photolibrary.dagger.components

import android.app.Application
import com.hamza.photolibrary.PhotoLibraryApp
import com.hamza.photolibrary.dagger.modules.*
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
        NetworkModule::class,
        ReposModule::class
    ]
)

interface AppComponent : AndroidInjector<PhotoLibraryApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: PhotoLibraryApp)
}