package com.odogwudev.mixedcookingrecipes.abstraction

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.odogwudev.mixedcookingrecipes.listeners.ItemClickListener
import com.odogwudev.mixedcookingrecipes.models.LocalModel

abstract class AbstractViewHolder(itemView: View, callback: ItemClickListener? = null) :
    RecyclerView.ViewHolder(itemView) {

    init {
        itemView.setOnClickListener {
            callback?.onItemClick(it)
        }
    }

    fun bindData(data: LocalModel) {
        itemView.tag = data
        presentData(data)
    }

    abstract fun presentData(data: LocalModel)
}