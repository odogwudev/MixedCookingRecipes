package com.odogwudev.mixedcookingrecipes.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.odogwudev.mixedcookingrecipes.R
import com.odogwudev.mixedcookingrecipes.abstraction.AbstractActivity
import com.odogwudev.mixedcookingrecipes.ui.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingActivity : AbstractActivity(R.layout.activity_onboarding) {

    private lateinit var viewModel: OnboardingRepository

    override fun init() {
        viewModel = ViewModelProvider(this).get(OnboardingRepository::class.java)
    }

    override fun running() {
        onboarding_viewpager.adapter = viewModel.adapter

        next_btn.setOnClickListener {
            when (onboarding_viewpager.currentItem) {
                0 -> onboarding_viewpager.currentItem = 1
                1 -> onboarding_viewpager.currentItem = 2
                2 -> Unit
            }
        }

        get_started_btn.setOnClickListener {
            goToHomepage()
        }

        onbording_skip_btn.setOnClickListener {
            goToHomepage()
        }
    }

    override fun stopped() {}

    fun goToHomepage() {
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
    }
}