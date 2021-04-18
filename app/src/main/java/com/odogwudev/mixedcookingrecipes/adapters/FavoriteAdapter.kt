package com.odogwudev.mixedcookingrecipes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.odogwudev.mixedcookingrecipes.R
import com.odogwudev.mixedcookingrecipes.abstraction.DiffUtilClass
import com.odogwudev.mixedcookingrecipes.listeners.ItemClickListener
import com.odogwudev.mixedcookingrecipes.models.EmptyModel
import com.odogwudev.mixedcookingrecipes.models.LocalModel
import com.odogwudev.mixedcookingrecipes.models.RecipeMain
import com.odogwudev.mixedcookingrecipes.models.ShimmerModel

class FavoriteAdapter(private val callback: ItemClickListener) :
    ListAdapter<LocalModel, FavoriteViewHolder>(DiffUtilClass<LocalModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return FavoriteViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is RecipeMain -> R.layout.holder_favorite_item
        is ShimmerModel -> R.layout.holder_shimmer_favorite_item
        is EmptyModel -> R.layout.holder_empty_favorite_item
        else -> R.layout.holder_empty_item
    }
}