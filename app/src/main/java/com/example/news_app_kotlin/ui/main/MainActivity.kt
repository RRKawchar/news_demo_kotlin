package com.example.news_app_kotlin.ui.main


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.news_app_kotlin.R
import com.example.news_app_kotlin.ui.categories.CategoriesFragment
import com.example.news_app_kotlin.ui.home.HomeFragment
import com.example.news_app_kotlin.ui.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottom_nav)

        // Default page = Home
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.navigation_home -> {
                    loadFragment(HomeFragment())
                    true
                }

                R.id.navigation_categories -> {
                    loadFragment(CategoriesFragment())
                    true
                }

                R.id.navigation_profile -> {
                    loadFragment(ProfileFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}

