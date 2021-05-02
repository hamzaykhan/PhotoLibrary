package com.hamza.photolibrary.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hamza.photolibrary.model.PhotoModel
import javax.inject.Inject

class PictureDetailViewModel @Inject constructor() : ViewModel() {

    private var _uiState = MutableLiveData<PictureDetailUiState>()
    var uiStateLiveData: LiveData<PictureDetailUiState> = _uiState

    private var _photoModel = MutableLiveData<PhotoModel>()
    var photoModelLiveData: LiveData<PhotoModel> = _photoModel

    fun initPhotoModel(photo: PhotoModel) {
        _photoModel.value = photo
    }
}