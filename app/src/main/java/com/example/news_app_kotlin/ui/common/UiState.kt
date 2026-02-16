package com.example.news_app_kotlin.ui.common

import com.example.news_app_kotlin.data.remote.models.Article

sealed class UiState {
    object Loading : UiState()
    data class Success(val data: List<Article>) : UiState()
    data class Error(val message: String) : UiState()
}
