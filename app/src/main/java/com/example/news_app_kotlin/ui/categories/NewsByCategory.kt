package com.example.news_app_kotlin.ui.categories

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news_app_kotlin.R
import com.example.news_app_kotlin.data.repositories.NewsRepository
import com.example.news_app_kotlin.ui.common.UiState
import com.example.news_app_kotlin.ui.details.NewsDetailsPage
import com.example.news_app_kotlin.ui.home.NewsAdapter
import com.google.android.material.appbar.MaterialToolbar

/*
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
      //---------------------------------------------------
        val recyclerView = findViewById<RecyclerView>(R.id.categoryFVNews)

        val newsAdapter = NewsAdapter { article ->
            Toast.makeText(this, article.title, Toast.LENGTH_SHORT).show()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = newsAdapter

        //---------------------------------------------------



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
     */
/*   viewModel.categoryNews.observe(this) { state ->
            when(state) {
                is UiState.Loading -> println("Loading...")
                is UiState.Success<*> -> println("Success: ${state.data}")
                is UiState.Error -> println("Error----------: ${state.message}")
            }
        }*//*


        viewModel.categoryNews.observe(this) { state ->
            when(state) {

                is UiState.Loading -> {
                    // optional: show progress bar
                }

                is UiState.Success -> {
                    newsAdapter.setData(state.data)
                }

                is UiState.Error -> {
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Trigger API call
        viewModel.fetchCategoryNews(categoryName)

    }
}*/



class NewsByCategory : AppCompatActivity() {

    private lateinit var newsAdapter: NewsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvNotFound: TextView



    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        onBackPressedDispatcher.addCallback(this) {

            val fragmentContainer = findViewById<FrameLayout>(R.id.fragment_container)

            if (fragmentContainer.visibility == View.VISIBLE) {

                supportFragmentManager.popBackStack()

                fragmentContainer.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE

            } else {
                finish()
            }
        }

        setContentView(R.layout.activity_news_by_category)

        recyclerView = findViewById(R.id.categoryFVNews)
        progressBar = findViewById(R.id.progressBar)
        tvNotFound = findViewById(R.id.tvNotFound)

        // 3️ Initialize Adapter with click listener
        newsAdapter = NewsAdapter { article ->

            val bundle = Bundle().apply {
                putString("title", article.title)
                putString("description", article.description)
                putString("image", article.image_url)
                putString("link", article.link)
                putString("source_icon", article.source_icon)
                putString("datatype", article.datatype)
            }

            val detailsFragment = NewsDetailsPage()
            detailsFragment.arguments = bundle

            recyclerView.visibility = View.GONE
            findViewById<FrameLayout>(R.id.fragment_container).visibility = View.VISIBLE

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, detailsFragment)
                .addToBackStack("details")
                .commit()
        }


        recyclerView.layoutManager = LinearLayoutManager(this)
     /*   newsAdapter = NewsAdapter { article ->
            Toast.makeText(this,"sdfgjdfgh", Toast.LENGTH_SHORT).show()
        }*/
        recyclerView.adapter = newsAdapter

        val categoryName = intent.getStringExtra("category")

        val toolbar= findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = categoryName ?: "News"
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        val viewModel = ViewModelProvider(this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CategoryViewModel(NewsRepository()) as T
                }
            }
        )[CategoryViewModel::class.java]

        // Observe category news
        viewModel.categoryNews.observe(this) { state ->
            when(state) {
                is UiState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    tvNotFound.visibility = View.GONE
                }
                is UiState.Success -> {
                    progressBar.visibility = View.GONE
                    if(state.data.isEmpty()) {
                        tvNotFound.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    } else {
                        recyclerView.visibility = View.VISIBLE
                        tvNotFound.visibility = View.GONE
                        newsAdapter.setData(state.data)
                    }
                }
                is UiState.Error -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                    tvNotFound.visibility = View.VISIBLE
                    tvNotFound.text = "Error: ${state.message}"
                }
            }
        }

        // Trigger API call
        viewModel.fetchCategoryNews(categoryName)
    }



}
