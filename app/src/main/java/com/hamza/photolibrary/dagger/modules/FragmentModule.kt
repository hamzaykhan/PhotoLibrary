package com.hamza.photolibrary.dagger.modules

import com.hamza.photolibrary.ui.detail.PictureDetailFragment
import com.hamza.photolibrary.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * The dagger module for fragment
 */
@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun bindPictureDetailsFragment(): PictureDetailFragment
}