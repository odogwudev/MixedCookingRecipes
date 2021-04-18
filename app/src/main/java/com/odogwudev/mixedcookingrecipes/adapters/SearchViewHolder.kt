package com.odogwudev.mixedcookingrecipes.adapters

import android.view.View
import com.bumptech.glide.Glide
import com.odogwudev.mixedcookingrecipes.R
import com.odogwudev.mixedcookingrecipes.abstraction.AbstractViewHolder
import com.odogwudev.mixedcookingrecipes.listeners.ItemClickListener
import com.odogwudev.mixedcookingrecipes.models.LocalModel
import com.odogwudev.mixedcookingrecipes.models.QueryModel
import com.odogwudev.mixedcookingrecipes.models.RecipeMain
import kotlinx.android.synthetic.main.holder_search_results_card_item.view.*
import kotlinx.android.synthetic.main.holder_search_results_item.view.*

class SearchViewHolder(itemView: View, callback: ItemClickListener) :
    AbstractViewHolder(itemView, callback) {

    override fun presentData(data: LocalModel) {
        when (data) {
            is RecipeMain -> {
                Glide.with(itemView).load(data.recipe.image)
                    .placeholder(R.mipmap.mixedcookingrecipes_app_icon)
                    .into(itemView.recipe_card_item_img)
                itemView.recipe_card_item_label.text = data.recipe.label
            }

            is QueryModel -> {
                itemView.search_results_item_label.text = data.queryName
            }
        }
    }
}