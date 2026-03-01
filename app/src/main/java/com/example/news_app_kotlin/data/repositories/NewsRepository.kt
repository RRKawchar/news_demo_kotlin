package com.example.news_app_kotlin.data.repositories
import com.example.news_app_kotlin.data.remote.api.ApiConstants
import com.example.news_app_kotlin.data.remote.api.RetrofitClient

class NewsRepository {

    suspend fun getNews() = RetrofitClient.getLatestNews.getLatestNews(apiKey = ApiConstants.NEWS_DATA_API_KEY)
    suspend fun getCategoryNews(category:String) = RetrofitClient.getCategoryNews.getCategoryNews(
        country="us",
        category = category,
        apiKey = ApiConstants.NEWS_ORG_API_KEY
    )
}
