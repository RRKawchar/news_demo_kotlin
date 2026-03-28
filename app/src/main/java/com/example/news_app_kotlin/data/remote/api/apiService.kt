package com.example.news_app_kotlin.data.remote.api


import com.example.news_app_kotlin.data.remote.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/1/latest")
    suspend fun getLatestNews(
        @Query("apiKey") apiKey:String
    ): Response<NewsResponse>


    @GET("api/1/latest")
    suspend fun getCategoryNews(
        @Query("apikey") apiKey: String,
        @Query("category") category: String,
        @Query("country") country: String? = null,
        @Query("language") language: String? = "en"
    ): Response<NewsResponse>
}
