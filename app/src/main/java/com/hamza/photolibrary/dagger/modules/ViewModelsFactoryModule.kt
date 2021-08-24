package com.hamza.photolibrary.dagger.modules

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
interface ViewModelsFactoryModule {
    @Binds
    fun bindAppViewModelFactory(factory: AppViewModelProviderFactory) : ViewModelProvider.Factory
}