package com.odogwudev.mixedcookingrecipes.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.odogwudev.mixedcookingrecipes.R
import com.odogwudev.mixedcookingrecipes.abstraction.DiffUtilClass
import com.odogwudev.mixedcookingrecipes.listeners.ItemClickListener
import com.odogwudev.mixedcookingrecipes.models.HomeCategoryItem
import com.odogwudev.mixedcookingrecipes.models.LocalModel
import com.odogwudev.mixedcookingrecipes.models.SuggestionItem

class CategoriesAdapter(private val callback: ItemClickListener) :
    ListAdapter<LocalModel, CategoriesViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return CategoriesViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is SuggestionItem -> R.layout.holder_category_item_row
        is HomeCategoryItem -> R.layout.holder_categories_item_row
        else -> R.layout.holder_empty_item
    }
}