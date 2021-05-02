package com.hamza.photolibrary.di.modules

import com.hamza.photolibrary.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * The dagger module for activities
 */
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}