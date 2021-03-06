package com.odogwudev.mixedcookingrecipes.ui.dashboard.profile

import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.odogwudev.mixedcookingrecipes.adapters.FavoriteAdapter
import com.odogwudev.mixedcookingrecipes.listeners.ItemClickListener
import com.odogwudev.mixedcookingrecipes.listeners.RecipeClickListener
import com.odogwudev.mixedcookingrecipes.models.EmptyModel
import com.odogwudev.mixedcookingrecipes.models.RecipeMain
import com.odogwudev.mixedcookingrecipes.models.ShimmerModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel(), ItemClickListener {

    private val repo = ProfileRepository()
    val data = repo.data
    val emptyFavorites = repo.emptyFavorites
    val userEmail = repo.userEmail
    val userImageLink = repo.userImageLink
    val username = repo.username
    val adapter = FavoriteAdapter(this)
    private lateinit var callback: RecipeClickListener

    init {
        adapter.submitList(listOf(ShimmerModel(), ShimmerModel(), ShimmerModel()))
    }

    fun getFavoriteData(callback: RecipeClickListener) {
        this.callback = callback

        CoroutineScope(Dispatchers.IO).launch { repo.getUserFavorites() }
    }

    override fun onItemClick(view: View) {
        when (view.tag) {
            is RecipeMain -> callback.onRecipeClick(view.tag as RecipeMain)
        }
    }

    fun observe(owner: LifecycleOwner) {
        data.observe(owner, Observer {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })

        emptyFavorites.observe(owner, Observer {
            when (it) {
                true -> {
                    adapter.submitList(listOf(EmptyModel()))
                    adapter.notifyDataSetChanged()
                }
                false -> Unit
            }
        })
    }

    fun removeObservers(owner: LifecycleOwner) {
        data.removeObservers(owner)
        emptyFavorites.removeObservers(owner)
    }

    fun saveCameraPhotoToDb(imgBitmap: Bitmap) {
        repo.saveCameraPhotoToDb(imgBitmap)
    }

    fun saveGalleryPhotoToDb(imageUri: Uri) {
        repo.saveGalleryPhotoToDb(imageUri)
    }

    fun getUserPhoto() {
        repo.getUserPhoto()
    }

    fun getUserProfileData() {
        CoroutineScope(Dispatchers.IO).launch {
            repo.getUserProfileData()
        }
    }
}