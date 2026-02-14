package com.example.news_app_kotlin.ui.home

import androidx.lifecycle.*
import com.example.news_app_kotlin.data.remote.models.NewsResponse
import com.example.news_app_kotlin.data.repositories.NewsRepository

import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val repository = NewsRepository()

    private val _news = MutableLiveData<Response<NewsResponse>>()
    val news: LiveData<Response<NewsResponse>> = _news

    fun fetchNews() {
        viewModelScope.launch {
            val response = repository.getNews()
            _news.postValue(response)
        }
    }
}
