package com.odogwudev.mixedcookingrecipes.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.odogwudev.mixedcookingrecipes.abstraction.AbstractViewHolder
import com.odogwudev.mixedcookingrecipes.listeners.ItemClickListener
import com.odogwudev.mixedcookingrecipes.models.HomeCategoryItem
import com.odogwudev.mixedcookingrecipes.models.LocalModel
import com.odogwudev.mixedcookingrecipes.models.SuggestionItem
import kotlinx.android.synthetic.main.holder_categories_item_row.view.*
import kotlinx.android.synthetic.main.holder_category_item_row.view.*

class CategoriesViewHolder(itemView: View, callback: ItemClickListener) :
    AbstractViewHolder(itemView, callback) {

    override fun presentData(data: LocalModel) {
        when (data) {
            is SuggestionItem -> {
                itemView.category_img.setImageResource(data.categoryImg)
                itemView.category_txt.text = data.categoryName
            }

            is HomeCategoryItem -> {
                itemView.categories_img.setImageResource(data.categoryImg)
                itemView.categories_name.text = data.categoryName
            }
        }
    }
}