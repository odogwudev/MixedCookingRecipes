package com.odogwudev.mixedcookingrecipes.adapters


import android.view.View
import com.bumptech.glide.Glide
import com.odogwudev.mixedcookingrecipes.R
import com.odogwudev.mixedcookingrecipes.abstraction.AbstractViewHolder
import com.odogwudev.mixedcookingrecipes.listeners.ItemClickListener
import com.odogwudev.mixedcookingrecipes.models.LocalModel
import com.odogwudev.mixedcookingrecipes.models.RecipeMain
import kotlinx.android.synthetic.main.holder_homepage_carousel_item.view.*

class HomepageViewHolder(itemView: View, callback: ItemClickListener) :
    AbstractViewHolder(itemView, callback) {

    override fun presentData(data: LocalModel) {
        when (data) {
            is RecipeMain -> {
                itemView.recipe_carousel_recipe_name.text = data.recipe.label
                Glide.with(itemView).load(data.recipe.image).placeholder(R.mipmap.mixedcookingrecipes_app_icon)
                    .into(itemView.recipe_carousel_img)
            }
        }
    }
}