package com.example.news_app_kotlin.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news_app_kotlin.R

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var viewModel: HomeViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        // Adapter initialize
        newsAdapter = NewsAdapter()

        // RecyclerView setup
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvNews)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = newsAdapter

        // API call
        viewModel.fetchNews()

        // Observe and send data to adapter
        viewModel.news.observe(viewLifecycleOwner) { response ->
            if (response.isSuccessful) {
                val articles = response.body()?.results ?: emptyList()
                newsAdapter.setData(articles)
            }
        }
    }
}
