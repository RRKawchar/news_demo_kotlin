package com.example.news_app_kotlin.data.remote.models


data class Article(
    val title: String?,
    val description: String?,
    val link: String?,
    val image_url: String?,
    val datatype: String?,
    val source_icon: String?
)
