package com.odogwudev.mixedcookingrecipes.ui.details

import android.content.Intent
import android.content.res.ColorStateList
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.odogwudev.mixedcookingrecipes.R
import com.odogwudev.mixedcookingrecipes.abstraction.AbstractActivity
import com.odogwudev.mixedcookingrecipes.models.RecipeMain
import com.odogwudev.mixedcookingrecipes.ui.webview.WebviewActivity
import com.odogwudev.mixedcookingrecipes.utils.formatNumber
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AbstractActivity(R.layout.activity_details) {

    private lateinit var viewModel: DetailsViewModel
    private lateinit var recipe: RecipeMain
    private var isFavorite = false

    override fun init() {
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    override fun running() {
        recipe = intent.getParcelableExtra<RecipeMain?>("RECIPE")!!
        Log.d("Recipe Item", recipe.toString())

        viewModel.isFavoriteRecipe(recipe)
        presentRecipeData(recipe)

        viewModel.getCookTime(recipe.recipe.totalTime)

        share_btn.setOnClickListener {
            startActivity(Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(
                    Intent.EXTRA_TEXT,
                    "I think that you might be interested in ${recipe.recipe.label}. Check it out here: ${recipe.recipe.url}"
                )
            })
        }

        like_btn.setOnClickListener {
            when (isFavorite) {
                true -> viewModel.removeRecipeFromFavorites(recipe)
                false -> viewModel.addRecipeToFavorites(recipe)
            }
        }

        open_recipe_btn.setOnClickListener {
            startActivity(
                Intent(this, WebviewActivity::class.java)
                    .putExtra("URL", recipe.recipe.url)
            )
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.cookTime.observe(this, Observer {
            recipe_time.text = it
        })

        viewModel.isFavoriteRecipe.observe(this, Observer {
            isFavorite = it
            when (it) {
                true -> {
                    like_btn.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            applicationContext,
                            R.color.red
                        )
                    )
                }

                false -> {
                    like_btn.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            applicationContext,
                            R.color.yellow
                        )
                    )
                }
            }
        })
    }

    private fun presentRecipeData(item: RecipeMain) {
        Glide.with(this).load(item.recipe.image)
            .placeholder(R.mipmap.mixedcookingrecipes_app_icon).into(recipe_details_img)

        recipe_detail_label.text = item.recipe.label
        recipe_time.text = item.recipe.totalTime.toString()
        calories.text = formatNumber(item.recipe.calories)
        protein.text = item.recipe.totalNutrients.FAMS?.quantity?.let { formatNumber(it) }
        carbs.text = item.recipe.totalNutrients.CHOCDF?.quantity?.let { formatNumber(it) }
        fats.text = item.recipe.totalNutrients.FAT?.quantity?.let { formatNumber(it) }
    }

    override fun stopped() {
        viewModel.cookTime.removeObservers(this)
        viewModel.isFavoriteRecipe.removeObservers(this)
    }
}