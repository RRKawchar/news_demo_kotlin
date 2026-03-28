package com.example.news_app_kotlin.ui.categories

import android.util.Log
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_app_kotlin.data.remote.models.Article
import com.example.news_app_kotlin.data.repositories.NewsRepository
import com.example.news_app_kotlin.ui.common.UiState
import kotlinx.coroutines.launch
import java.util.Locale

class CategoryViewModel(private  val repository: NewsRepository): ViewModel() {

  private  val _categoryNews= MutableLiveData< UiState<List<Article>>>()
  val categoryNews: LiveData<UiState<List<Article>>> = _categoryNews
    fun fetchCategoryNews(category: String?){
     viewModelScope.launch {
         _categoryNews.value= UiState.Loading
          try {
              val catName= category?.lowercase(Locale.ROOT)
              Log.d("category name check", catName.toString())
              val response = repository.getCategoryNews(catName.toString())
              if(response.isSuccessful){
                  val article=response.body()?.results ?:emptyList()
                  _categoryNews.value=UiState.Success(article)
              } else {
                  Log.d("API_ERROR", response.errorBody()?.string().toString())
                  _categoryNews.value = UiState.Error("Something went wrong")
              }

          } catch (e: Exception) {
              _categoryNews.value = UiState.Error(e.message ?: "Unknown Error")
          }
     }
    }
}