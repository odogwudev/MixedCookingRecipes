package com.odogwudev.mixedcookingrecipes.ui.results

import androidx.lifecycle.MutableLiveData
import com.odogwudev.mixedcookingrecipes.APP_ID
import com.odogwudev.mixedcookingrecipes.APP_KEY
import com.odogwudev.mixedcookingrecipes.models.RecipeMain
import com.odogwudev.mixedcookingrecipes.models.ResponseModel
import com.odogwudev.mixedcookingrecipes.network.ApiClient
import com.odogwudev.mixedcookingrecipes.utils.ApiPagingHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilterResultsRepository {

    val data = MutableLiveData<List<RecipeMain>>()
    val errorData = MutableLiveData<Boolean>()
    private var recipes: MutableList<RecipeMain> = arrayListOf()
    private val apiPager = ApiPagingHelper()

    fun getDataFromApi(
        kcalMinValue: Int,
        kcalMaxValue: Int,
        mealType: String,
        dietType: String
    ) {
        apiPager.incrementCounters()

        ApiClient.getCustomRecipes(
            apiPager.from, apiPager.to,
            "",
            "${kcalMinValue}-${kcalMaxValue}",
            mealType,
            dietType,
            APP_ID,
            APP_KEY
        ).enqueue(object : Callback<ResponseModel> {
            override fun onResponse(
                call: Call<ResponseModel>,
                response: Response<ResponseModel>
            ) {
                response.body()!!.hits.forEach {
                    recipes.add(it)
                }

                when (recipes.size) {
                    0 -> errorData.value = true
                    else -> {
                        data.value = recipes
                        errorData.value = false
                    }
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                errorData.value = true
            }
        })
    }

    fun clearCounters() {
        apiPager.clearCounters()
    }
}