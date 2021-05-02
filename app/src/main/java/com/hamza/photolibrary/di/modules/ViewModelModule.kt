package com.hamza.photolibrary.di.modules

import androidx.lifecycle.ViewModel
import com.hamza.photolibrary.ui.detail.PictureDetailViewModel
import com.hamza.photolibrary.ui.home.HomeViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.PROPERTY_GETTER
)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PictureDetailViewModel::class)
    abstract fun bindPictureDetailViewModel(viewModel: PictureDetailViewModel): ViewModel
}