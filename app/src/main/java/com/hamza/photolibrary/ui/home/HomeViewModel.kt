package com.hamza.photolibrary.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamza.photolibrary.data.DataState
import com.hamza.photolibrary.data.usecases.FetchPopularPhotosUsecase
import com.hamza.photolibrary.data.usecases.SearchPhotosUsecase
import com.hamza.photolibrary.model.PhotoModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val fetchPopularPhotosUsecase: FetchPopularPhotosUsecase,
    private val searchPhotosUsecase: SearchPhotosUsecase
) : ViewModel() {

    private var _uiState = MutableLiveData<HomeUiState>()
    var uiStateLiveData: LiveData<HomeUiState> = _uiState

    private var _photosList = MutableLiveData<List<PhotoModel>>()
    var photosListLiveData: LiveData<List<PhotoModel>> = _photosList

    private var pageNumber = 1
    private var searchQuery: String = ""

    init {
        fetchPhotos(pageNumber)
    }

    fun loadMorePhotos() {
        pageNumber++
        if (searchQuery == "")
            fetchPhotos(pageNumber)
        else
            searchPhotos(searchQuery, pageNumber)
    }

    fun retry() {
        if (searchQuery == "")
            fetchPhotos(pageNumber)
        else
            searchPhotos(searchQuery, pageNumber)
    }

    fun searchPhotos(query: String) {
        searchQuery = query
        pageNumber = 1
        searchPhotos(query, pageNumber)
    }

    fun fetchPhotos(page: Int) {
        _uiState.postValue(if (page == 1) LoadingState else LoadingNextPageState)
        viewModelScope.launch {
            fetchPopularPhotosUsecase(page).collect { dataState ->
                when(dataState) {
                    is DataState.Success -> {
                        if (page == 1) {
                            // First page
                            _uiState.postValue(ContentState)
                            _photosList.postValue(dataState.data)
                        } else {
                            // Any other page
                            _uiState.postValue(ContentNextPageState)
                            var currentList = arrayListOf<PhotoModel>()
                            _photosList.value?.let { currentList.addAll(it) }
                            currentList.addAll(dataState.data)
                            _photosList.postValue(currentList)
                        }
                    }

                    is DataState.Error -> {
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

    private fun searchPhotos(query: String, page: Int) {
        _uiState.postValue(if (page == 1) LoadingState else LoadingNextPageState)
        viewModelScope.launch {
            searchPhotosUsecase(query, page).collect { dataState ->
                when(dataState) {
                    is DataState.Success -> {
                        if (page == 1) {
                            // First page
                            _uiState.postValue(ContentState)
                            _photosList.postValue(dataState.data)
                        } else {
                            // Any other page
                            _uiState.postValue(ContentNextPageState)
                            var currentList = arrayListOf<PhotoModel>()
                            _photosList.value?.let { currentList.addAll(it) }
                            currentList.addAll(dataState.data)
                            _photosList.postValue(currentList)
                        }
                    }

                    is DataState.Error -> {
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