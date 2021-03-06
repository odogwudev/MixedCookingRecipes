package com.odogwudev.mixedcookingrecipes.ui.intro

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseUser
import com.odogwudev.mixedcookingrecipes.R
import com.odogwudev.mixedcookingrecipes.abstraction.AbstractActivity
import com.odogwudev.mixedcookingrecipes.ui.dashboard.DashboardActivity
import com.odogwudev.mixedcookingrecipes.ui.login.LoginActivity
import com.odogwudev.mixedcookingrecipes.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AbstractActivity(R.layout.activity_intro) {

    private lateinit var viewModel: IntroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IntroViewModel::class.java)
    }

    override fun init() {}

    override fun onStart() {
        super.onStart()
        val currentUser = viewModel.auth.currentUser
        updateUI(currentUser)
    }

    override fun running() {
        intro_login_btn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        intro_register_btn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    override fun stopped() {}

    private fun updateUI(user: FirebaseUser?) {
        user?.let { startActivity(Intent(this, DashboardActivity::class.java)) }
    }

    override fun onBackPressed() {
        // You can't go back | Log in to proceed
    }
}