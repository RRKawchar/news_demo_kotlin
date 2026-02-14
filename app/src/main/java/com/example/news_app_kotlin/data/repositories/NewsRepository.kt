package com.example.news_app_kotlin.data.repositories
import com.example.news_app_kotlin.data.remote.api.RetrofitClient

class NewsRepository {

    suspend fun getNews() = RetrofitClient.api.getLatestNews()
}
