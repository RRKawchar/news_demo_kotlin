package com.example.news_app_kotlin.ui.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_app_kotlin.data.remote.models.Article
import com.example.news_app_kotlin.data.repositories.NewsRepository
import com.example.news_app_kotlin.ui.common.UiState
import kotlinx.coroutines.launch

class CategoryViewModel(private  val repository: NewsRepository): ViewModel() {

  private  val _categoryNews= MutableLiveData< UiState<List<Article>>>()
  val categoryNews: LiveData<UiState<List<Article>>> = _categoryNews
    fun fetchCategoryNews(category: String?){
     viewModelScope.launch {
         _categoryNews.value= UiState.Loading
          try {
              val response = repository.getCategoryNews(category)
              if(response.isSuccessful){
                  val article=response.body()?.results ?:emptyList()
                  _categoryNews.value=UiState.Success(article)
              } else {
                  _categoryNews.value = UiState.Error("Something went wrong")
              }

          } catch (e: Exception) {
              _categoryNews.value = UiState.Error(e.message ?: "Unknown Error")
          }
     }
    }
}