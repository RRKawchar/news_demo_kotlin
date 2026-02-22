package com.example.news_app_kotlin.ui.categories
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.news_app_kotlin.R
import com.example.news_app_kotlin.data.remote.models.Category

class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    private lateinit var recyclerView: RecyclerView

    private val list: List<Category> = listOf(
        Category("Business"),
        Category("Technology"),
        Category("Sports"),
        Category("Politics"),
        Category("Entertainment"),
        Category("Health"),
        Category("Science"),
        Category("Education"),
        Category("Music"),
        Category("Travel"),
        Category("Food"),
        Category("Fashion"),
        Category("Economy"),
        Category("World"),
        Category("Environment")
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerCategories)

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = CategoriesAdapter(list)
    }
}
