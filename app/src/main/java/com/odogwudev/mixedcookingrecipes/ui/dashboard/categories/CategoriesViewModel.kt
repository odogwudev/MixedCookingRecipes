package com.odogwudev.mixedcookingrecipes.ui.dashboard.categories

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.odogwudev.mixedcookingrecipes.adapters.CategoriesAdapter
import com.odogwudev.mixedcookingrecipes.listeners.ItemClickListener
import com.odogwudev.mixedcookingrecipes.listeners.SuggestionItemClickListener
import com.odogwudev.mixedcookingrecipes.models.SuggestionItem

class CategoriesViewModel : ViewModel(), ItemClickListener {

    private val repo = CategoriesRepository()
    val categories = repo.categories
    val adapter = CategoriesAdapter(this)
    private lateinit var callback: SuggestionItemClickListener

    fun initCategories() {
        repo.getCategories()
    }

    fun observeData(owner: LifecycleOwner, callback: SuggestionItemClickListener) {
        this.callback = callback
        categories.observe(owner, Observer {
            adapter.submitList(it)
        })
    }

    fun removeObserver(owner: LifecycleOwner) {
        categories.removeObservers(owner)
    }

    override fun onItemClick(view: View) {
        when (view.tag) {
            is SuggestionItem -> {
                callback.onCategoryClick(view.tag as SuggestionItem)
            }
        }
    }
}