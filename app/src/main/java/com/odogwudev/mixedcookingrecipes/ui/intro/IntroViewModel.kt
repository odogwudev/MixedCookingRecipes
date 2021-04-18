package com.odogwudev.mixedcookingrecipes.ui.intro

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class IntroViewModel : ViewModel() {

    var auth: FirebaseAuth = FirebaseAuth.getInstance()
}