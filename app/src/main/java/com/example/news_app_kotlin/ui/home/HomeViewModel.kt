package com.example.news_app_kotlin.ui.home

import androidx.lifecycle.*
import com.example.news_app_kotlin.data.remote.models.NewsResponse
import com.example.news_app_kotlin.data.repositories.NewsRepository
import com.example.news_app_kotlin.ui.common.UiState

import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val repository = NewsRepository()
    private val _newsState = MutableLiveData<UiState>()
    val newsState: LiveData<UiState> = _newsState

    private val _news = MutableLiveData<Response<NewsResponse>>()
    val news: LiveData<Response<NewsResponse>> = _news


    fun fetchNews() {
        if (_newsState.value is UiState.Success) return
        viewModelScope.launch {
            _newsState.value = UiState.Loading

           try {

               val response = repository.getNews()

               if(response.isSuccessful){
                   val articles = response.body()?.results ?: emptyList()
                   _newsState.value = UiState.Success(articles)

               } else {
                   _newsState.value = UiState.Error("Something went wrong")
               }
//               _news.postValue(response)


           }catch (e: Exception){
               _newsState.value = UiState.Error(e.message ?: "Error")
           }

        }
    }
}
