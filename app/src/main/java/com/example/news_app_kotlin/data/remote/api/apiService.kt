package com.example.news_app_kotlin.data.remote.api


import com.example.news_app_kotlin.data.remote.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("api/1/latest?apikey=pub_512563725086efb6467c5a0c44913f0afafb2")
    suspend fun getLatestNews(): Response<NewsResponse>
}
