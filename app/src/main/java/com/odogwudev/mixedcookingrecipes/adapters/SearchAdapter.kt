package com.odogwudev.mixedcookingrecipes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.odogwudev.mixedcookingrecipes.R
import com.odogwudev.mixedcookingrecipes.abstraction.DiffUtilClass
import com.odogwudev.mixedcookingrecipes.listeners.ItemClickListener
import com.odogwudev.mixedcookingrecipes.models.EmptyModel
import com.odogwudev.mixedcookingrecipes.models.LocalModel
import com.odogwudev.mixedcookingrecipes.models.QueryModel
import com.odogwudev.mixedcookingrecipes.models.RecipeMain

class SearchAdapter(private val callback: ItemClickListener) :
    ListAdapter<LocalModel, SearchViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
        return SearchViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is RecipeMain -> R.layout.holder_search_results_card_item
        is QueryModel -> R.layout.holder_search_results_item
        is EmptyModel -> R.layout.holder_empty_query_item
        else -> R.layout.holder_empty_item
    }
}