package com.odogwudev.mixedcookingrecipes.ui.dashboard.categories

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.odogwudev.mixedcookingrecipes.R
import com.odogwudev.mixedcookingrecipes.abstraction.AbstractFragment
import com.odogwudev.mixedcookingrecipes.listeners.SuggestionItemClickListener
import com.odogwudev.mixedcookingrecipes.models.HomeCategoryItem
import com.odogwudev.mixedcookingrecipes.models.SuggestionItem
import com.odogwudev.mixedcookingrecipes.ui.categories.CategoriesResultsActivity
import kotlinx.android.synthetic.main.fragment_categories.*


class CategoriesFragment : AbstractFragment(R.layout.fragment_categories) {

    private lateinit var viewModel: CategoriesViewModel

    override fun init(view: View) {
        viewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)
    }

    override fun running() {
        viewModel.initCategories()

        categories_screen_recycler.adapter = viewModel.adapter

        viewModel.observeData(this, object : SuggestionItemClickListener {
            override fun onCategoryClick(category: SuggestionItem) {
                startActivity(Intent(context, CategoriesResultsActivity::class.java)
                    .putExtra("CATEGORY",category.categoryName))
            }

            override fun onHomeCategoryClick(category: HomeCategoryItem) {
                // Not a home category
            }
        })
    }

    override fun stop() {
        viewModel.removeObserver(this)
    }
}