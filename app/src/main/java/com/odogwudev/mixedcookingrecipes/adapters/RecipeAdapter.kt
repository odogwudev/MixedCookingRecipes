package com.odogwudev.mixedcookingrecipes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.odogwudev.mixedcookingrecipes.R
import com.odogwudev.mixedcookingrecipes.abstraction.DiffUtilClass
import com.odogwudev.mixedcookingrecipes.listeners.ItemClickListener
import com.odogwudev.mixedcookingrecipes.models.LocalModel
import com.odogwudev.mixedcookingrecipes.models.RecipeMain
import com.odogwudev.mixedcookingrecipes.models.ShimmerModel

class RecipeAdapter(private val callback: ItemClickListener) :
    ListAdapter<LocalModel, RecipeViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return RecipeViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is RecipeMain -> R.layout.holder_recipe_item_row
        is ShimmerModel -> R.layout.holder_shimmer_recipe_item_row
        else -> R.layout.holder_empty_item
    }
}