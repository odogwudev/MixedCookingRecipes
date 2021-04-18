package com.odogwudev.mixedcookingrecipes.ui.dashboard.main

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.odogwudev.mixedcookingrecipes.APP_ID
import com.odogwudev.mixedcookingrecipes.APP_KEY
import com.odogwudev.mixedcookingrecipes.R
import com.odogwudev.mixedcookingrecipes.models.HomeCategoryItem
import com.odogwudev.mixedcookingrecipes.models.ResponseModel
import com.odogwudev.mixedcookingrecipes.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragmentRepository {

    val databaseReference by lazy { FirebaseDatabase.getInstance().reference }
    val recipes = MutableLiveData<ResponseModel>()
    val username = MutableLiveData<String>()
    val homeCategories = MutableLiveData<List<HomeCategoryItem>>()

    fun getUsername() {
        databaseReference.child("users")
            .child(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .child("userData")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        username.value = snapshot.value.toString()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    username.value = null
                }
            })
    }

    fun getMealTypeResults(mealType: String) {
        ApiClient.getMealTypeResults(0, 10, "", mealType, APP_ID, APP_KEY)
            .enqueue(object : Callback<ResponseModel> {
                override fun onResponse(
                    call: Call<ResponseModel>,
                    response: Response<ResponseModel>
                ) {
                    recipes.value = response.body()
                }

                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    recipes.value = null
                }
            })
    }

    fun getHomeCategories() {
        homeCategories.value = listOf(
            HomeCategoryItem("Breakfast", R.drawable.breakfast),
            HomeCategoryItem("Lunch", R.drawable.lunch_icon),
            HomeCategoryItem("Soup", R.drawable.soup_icon),
            HomeCategoryItem("Salad", R.drawable.salad_icon),
            HomeCategoryItem("Dessert", R.drawable.desert_icon),
            HomeCategoryItem("Indian", R.drawable.indian_icon),
            HomeCategoryItem("Chinese", R.drawable.chinese_icon),
            HomeCategoryItem("Italian", R.drawable.pasta_icon)
        )
    }
}