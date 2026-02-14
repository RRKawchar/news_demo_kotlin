package com.example.news_app_kotlin.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.news_app_kotlin.R
import com.example.news_app_kotlin.data.remote.models.Article
class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var articleList = listOf<Article>()

    fun setData(list: List<Article>) {
        articleList = list
        notifyDataSetChanged()
    }

    class NewsViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articleList[position]
        holder.tvTitle.text = article.title
        holder.tvDescription.text = article.description
    }

    override fun getItemCount() = articleList.size
}
