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
    @GET("v2/top-headlines")
    suspend fun getCategoryNews(
        @Query("country")country:String,
        @Query("category")category:String,
        @Query("apiKey")apiKey:String
    ): Response<NewsResponse>
}
