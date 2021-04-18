package com.odogwudev.mixedcookingrecipes.ui.splash

import android.content.Intent
import android.os.Handler
import android.util.Log
import com.odogwudev.mixedcookingrecipes.R
import com.odogwudev.mixedcookingrecipes.abstraction.AbstractActivity
import com.odogwudev.mixedcookingrecipes.ui.intro.IntroActivity


class SplashActivity : AbstractActivity(R.layout.activity_splash) {

    override fun init() {
        Handler().postDelayed({
            startActivity(Intent(this, IntroActivity::class.java))
            finish()
        }, 3000)
    }

    override fun running() {}

    override fun stopped() {}
}