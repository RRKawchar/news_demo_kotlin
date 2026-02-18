package com.example.news_app_kotlin.ui.details
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.news_app_kotlin.R
import com.google.android.material.button.MaterialButton
import androidx.core.net.toUri

class NewsDetailsPage : Fragment(R.layout.fragment_news_details_page) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar(view)
        receiveAndShowData(view)

    }


    //  TOOLBAR SETUP
    private fun setupToolbar(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)

        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "News Read"
            setDisplayHomeAsUpEnabled(true)
        }

        toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    //  DATA RECEIVE AND SHOW -
    private fun receiveAndShowData(view: View) {
        // Bundle
        val title = arguments?.getString("title") ?: "No Title Available"
        val description = arguments?.getString("description") ?: "No Description Available"
        val imageUrl = arguments?.getString("image") ?: ""
        val link = arguments?.getString("link") ?: ""
        val sourceIcon = arguments?.getString("source_icon") ?: ""
        val dataType = arguments?.getString("datatype") ?: ""


        // Views এর সাথে Connect করা
        val ivArticleImage = view.findViewById<ImageView>(R.id.ivArticleImage)
        val tvNewsType = view.findViewById<TextView>(R.id.tvNewsType)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvDescription = view.findViewById<TextView>(R.id.tvDescription)
        val tvSourceIcon = view.findViewById<ImageView>(R.id.ivSourceIcon)
        val btnReadMore = view.findViewById<MaterialButton>(R.id.btnReadMore)

        // ===== DATA SHOW KORA =====

        // 1. Title Set
        tvTitle.text = title

        // 2. Description Set
        tvDescription.text = description

        // 3. News Type/Source
        tvNewsType.text = dataType



        //  Source Icon set
        if (sourceIcon.isNotEmpty()) {
            // Glide use for fist dependency add
            Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground) //if loading
                .error(R.drawable.ic_launcher_foreground) // if error
                .into(tvSourceIcon)
        } else {
            tvSourceIcon.setImageResource(R.drawable.ic_launcher_foreground)
        }
        //  Image Load
        if (imageUrl.isNotEmpty()) {
            // Glide use for fist dependency add
            Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background) //if loading
                .error(R.drawable.ic_launcher_background) // if error
                .into(ivArticleImage)
        } else {
            ivArticleImage.setImageResource(R.drawable.ic_launcher_background)
        }

        // 5. Read More Button এ Click Listener
        btnReadMore.setOnClickListener {
            // WebView or  Browser Article open
            val intent = Intent(Intent.ACTION_VIEW, link.toUri())
            startActivity(intent)

        }
    }
}
