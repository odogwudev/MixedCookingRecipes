package com.odogwudev.mixedcookingrecipes.listeners

import com.odogwudev.mixedcookingrecipes.models.RecipeMain

interface RecipeClickListener {

    fun onRecipeClick(recipe: RecipeMain)
}