package com.hamza.photolibrary.ui.detail

sealed class PictureDetailUiState

object LoadingState: PictureDetailUiState()
object ContentState: PictureDetailUiState()
class ErrorState(val message: String) : PictureDetailUiState()
