package com.example.news_app_kotlin.ui.categories

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.news_app_kotlin.R
import com.example.news_app_kotlin.data.repositories.NewsRepository
import com.example.news_app_kotlin.ui.common.UiState
import com.google.android.material.appbar.MaterialToolbar

class NewsByCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_news_by_category)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val categoryName = intent.getStringExtra("category")

        val toolbar= findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title=categoryName?:"News"

        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }



        // ViewModel
        val viewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CategoryViewModel(NewsRepository()) as T
                }
            }
        )[CategoryViewModel::class.java]



        // Observe LiveData
        viewModel.categoryNews.observe(this) { state ->
            when(state) {
                is UiState.Loading -> println("Loading...")
                is UiState.Success<*> -> println("Success: ${state.data}")
                is UiState.Error -> println("Error----------: ${state.message}")
            }
        }

        // Trigger API call
        viewModel.fetchCategoryNews(categoryName)

    }
}