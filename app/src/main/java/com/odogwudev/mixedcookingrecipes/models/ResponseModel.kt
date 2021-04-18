package com.odogwudev.mixedcookingrecipes.models

class ResponseModel(
    val q: String,
    val from: Int,
    val to: Int,
    val more: Boolean,
    val count: Int,
    val hits: List<RecipeMain>
) : LocalModel