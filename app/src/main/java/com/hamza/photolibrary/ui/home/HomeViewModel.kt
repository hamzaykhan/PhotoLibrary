package com.hamza.photolibrary.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamza.photolibrary.data.DataState
import com.hamza.photolibrary.data.PhotoLibraryRepository
import com.hamza.photolibrary.data.model.Photo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: PhotoLibraryRepository
) : ViewModel() {

    private var _uiState = MutableLiveData<HomeUiState>()
    var uiStateLiveData: LiveData<HomeUiState> = _uiState

    private var _photosList = MutableLiveData<List<Photo>>()
    var photosListLiveData: LiveData<List<Photo>> = _photosList

    private var pageNumber = 1

    init {
        fetchPhotos(pageNumber)
    }

    fun loadMorePhotos() {
        pageNumber++
        fetchPhotos(pageNumber)
    }

    fun retry() {
        fetchPhotos(pageNumber)
    }

    fun fetchPhotos(page: Int) {
        _uiState.postValue(if (page == 1) LoadingState else LoadingNextPageState)
        viewModelScope.launch {
            repository.loadPhotos(page).collect { dataState ->
                when(dataState) {
                    is DataState.Success<List<Photo>> -> {
                        if (page == 1) {
                            // First page
                            _uiState.postValue(ContentState)
                            _photosList.postValue(dataState.data)
                        } else {
                            // Any other page
                            _uiState.postValue(ContentNextPageState)
                            var currentList = arrayListOf<Photo>()
                            _photosList.value?.let { currentList.addAll(it) }
                            currentList.addAll(dataState.data)
                            _photosList.postValue(currentList)
                        }
                    }

                    is DataState.Error<List<Photo>> -> {
                        if (page == 1) {
                            _uiState.postValue(ErrorState(dataState.message))
                        } else {
                            _uiState.postValue(ErrorNextPageState(dataState.message))
                        }
                    }
                }
            }
        }
    }
}