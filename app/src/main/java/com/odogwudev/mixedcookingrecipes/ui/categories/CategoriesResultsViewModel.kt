package com.odogwudev.mixedcookingrecipes.ui.categories

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.odogwudev.mixedcookingrecipes.adapters.RecipeAdapter
import com.odogwudev.mixedcookingrecipes.listeners.ItemClickListener
import com.odogwudev.mixedcookingrecipes.listeners.RecipeClickListener
import com.odogwudev.mixedcookingrecipes.models.RecipeMain
import com.odogwudev.mixedcookingrecipes.models.ShimmerModel

class CategoriesResultsViewModel : ViewModel(), ItemClickListener {

    private val repo = CategoriesResultsRepository()
    val data = repo.data
    val isLoading = MutableLiveData<Boolean>()
    val adapter = RecipeAdapter(this)
    private lateinit var callback: RecipeClickListener

    init {
        adapter.submitList(
            listOf(
                ShimmerModel(),
                ShimmerModel(),
                ShimmerModel(),
                ShimmerModel(),
                ShimmerModel(),
                ShimmerModel(),
                ShimmerModel(),
                ShimmerModel()
            )
        )
    }

    fun getResults(cuisineType: String) {
        isLoading.value = true
        when (cuisineType) {
            "breakfast", "lunch" -> repo.getMealTypeResults(cuisineType)
            "soup", "salad", "dessert" -> repo.getDishTypeResults(cuisineType)
            "indian", "chinese", "italian" -> repo.getCuisineTypeResults(cuisineType)
            else -> Unit
        }
    }

    fun observeData(owner: LifecycleOwner, callback: RecipeClickListener) {
        this.callback = callback

        data.observe(owner, Observer {
            isLoading.value = false
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })
    }

    fun removeObserve(owner: LifecycleOwner) {
        data.removeObservers(owner)
    }

    fun loadMoreRecipes(cuisineType: String) {
        getResults(cuisineType)
    }

    fun clearCounters() {
        repo.clearCounters()
    }

    override fun onItemClick(view: View) {
        when (view.tag) {
            is RecipeMain -> {
                callback.onRecipeClick(view.tag as RecipeMain)
            }
        }
    }
}