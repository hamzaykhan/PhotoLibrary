package com.hamza.photolibrary.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hamza.photolibrary.data.model.Photo
import javax.inject.Inject

class PictureDetailViewModel @Inject constructor() : ViewModel() {

    private var _uiState = MutableLiveData<PictureDetailUiState>()
    var uiStateLiveData: LiveData<PictureDetailUiState> = _uiState

    private var _photoModel = MutableLiveData<Photo>()
    var photoLiveData: LiveData<Photo> = _photoModel

    fun initPhotoModel(photo: Photo) {
        _photoModel.value = photo
    }
}