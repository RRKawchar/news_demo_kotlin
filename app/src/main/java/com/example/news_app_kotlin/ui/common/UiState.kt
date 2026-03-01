package com.example.news_app_kotlin.ui.common

import com.example.news_app_kotlin.data.remote.models.Article

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}