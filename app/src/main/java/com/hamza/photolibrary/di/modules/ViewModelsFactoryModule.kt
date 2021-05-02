package com.hamza.photolibrary.di.modules

import androidx.lifecycle.ViewModelProvider
import com.hamza.photolibrary.di.factories.AppViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelsFactoryModule {
    @Binds
    fun bindAppViewModelFactory(factory: AppViewModelProviderFactory) : ViewModelProvider.Factory
}