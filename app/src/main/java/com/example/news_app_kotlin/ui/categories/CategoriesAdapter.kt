package com.example.news_app_kotlin.ui.categories

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.news_app_kotlin.R
import com.example.news_app_kotlin.data.remote.models.Category
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity

class CategoriesAdapter(private val list: List<Category>) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtCategory: TextView = itemView.findViewById(R.id.txtCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = list[position]
        holder.txtCategory.text = category.name

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context

            val intent = Intent(context, NewsByCategory::class.java)
            intent.putExtra("category", category.name)
            context.startActivity(intent)

/*
            Toast.makeText(
                holder.itemView.context,
                category.name,
                Toast.LENGTH_SHORT
            ).show()*/
        }
    }

    override fun getItemCount(): Int = list.size
}