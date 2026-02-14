package com.example.news_app_kotlin.data.remote.models


data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val results: List<Article>
)
