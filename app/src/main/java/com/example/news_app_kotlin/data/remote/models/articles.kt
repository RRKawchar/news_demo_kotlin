package com.example.news_app_kotlin.data.remote.models


data class Article(
    val title: String?=null,
    val description: String?=null,
    val link: String?=null,
    val image_url: String?=null,
    val datatype: String?=null,
    val source_icon: String?=null,
    val author: String? = null,
    val publishedAt: String? = null,
    val content: String? = null
)
