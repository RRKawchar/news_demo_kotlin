package com.example.news_app_kotlin.data.remote.api


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {


    /// if header need
    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->

                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer YOUR_TOKEN")
                    .addHeader("Accept", "application/json")
                    .build()

                chain.proceed(request)
            }
            .build()
    }

    val getLatestNews: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL_1)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    val getCategoryNews: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL_2)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
