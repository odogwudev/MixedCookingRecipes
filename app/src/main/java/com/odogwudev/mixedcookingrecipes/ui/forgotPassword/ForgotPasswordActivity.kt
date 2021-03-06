package com.odogwudev.mixedcookingrecipes.ui.forgotPassword


import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.odogwudev.mixedcookingrecipes.R
import com.odogwudev.mixedcookingrecipes.abstraction.AbstractActivity
import com.odogwudev.mixedcookingrecipes.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : AbstractActivity(R.layout.activity_forgot_password) {

    private lateinit var viewModel: ForgotPasswordViewModel

    override fun init() {
        viewModel = ViewModelProvider(this).get(ForgotPasswordViewModel::class.java)
    }

    override fun running() {
        forgot_pass_btn.setOnClickListener {
            viewModel.verifyEmail(forgot_pass_email.text.toString())
        }

        viewModel.userVerified.observe(this, Observer {
            when (it) {
                true -> showSuccessDialog()
                false -> Toast.makeText(this, "User not found", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun stopped() {
        viewModel.userVerified.removeObservers(this)
    }

    private fun showSuccessDialog() {
        Snackbar.make(
            findViewById(android.R.id.content),
            "The email has been sent. Please check your inbox",
            Snackbar.LENGTH_LONG
        )
            .show()

        startActivity(Intent(this, LoginActivity::class.java))
    }
}