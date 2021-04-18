package com.odogwudev.mixedcookingrecipes.ui.onboarding.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.odogwudev.mixedcookingrecipes.models.LocalModel
import com.odogwudev.mixedcookingrecipes.models.OnboardingItem
import kotlinx.android.synthetic.main.holder_onboarding_item.view.*

class OnboardingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun present(item: LocalModel) {
        when (item) {
            is OnboardingItem -> {
                itemView.onboarding_img.setImageResource(item.onboardingImg)
                itemView.onboarding_title.text = item.title
                itemView.onboarding_description.text = item.description
            }
        }
    }
}