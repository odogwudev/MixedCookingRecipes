package com.odogwudev.mixedcookingrecipes.listeners

import com.odogwudev.mixedcookingrecipes.models.HomeCategoryItem
import com.odogwudev.mixedcookingrecipes.models.SuggestionItem

interface SuggestionItemClickListener {

    fun onCategoryClick(category: SuggestionItem)
    fun onHomeCategoryClick(category: HomeCategoryItem)
}