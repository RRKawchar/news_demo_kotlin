package com.example.news_app_kotlin.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news_app_kotlin.R
import com.example.news_app_kotlin.data.remote.models.Article
class NewsAdapter( private val onItemClick: (Article) -> Unit) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var articleList = listOf<Article>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Article>) {
        articleList = list
        notifyDataSetChanged()
    }

    class NewsViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val ivNews: ImageView = itemView.findViewById(R.id.ivNews)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articleList[position]
        holder.tvTitle.text = article.title

        // Image load
        Glide.with(holder.itemView.context)
            .load(article.image_url)
            .into(holder.ivNews)

        // Click listener
        holder.itemView.setOnClickListener {
            onItemClick(article)
        }

    }

    override fun getItemCount() = articleList.size
}
