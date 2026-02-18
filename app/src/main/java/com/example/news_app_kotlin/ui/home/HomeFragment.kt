package com.example.news_app_kotlin.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news_app_kotlin.R
import com.example.news_app_kotlin.ui.common.UiState
import com.example.news_app_kotlin.ui.details.NewsDetailsPage

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var viewModel: HomeViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1️ Initialize views
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvNews)

        // 2️ Initialize ViewModel
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

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
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, detailsFragment)
                .addToBackStack(null)
                .commit()
            // Uncomment if using Navigation Component
            // findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
        }

        // 4️ Setup RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = newsAdapter

        // 5️ Observe UI State
        viewModel.newsState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                is UiState.Success -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    newsAdapter.setData(state.data)
                }
                is UiState.Error -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        // 6️ Fetch data once
        viewModel.fetchNews()
    }
}

