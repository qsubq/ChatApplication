package com.example.core

import androidx.annotation.StringRes

enum class AppRoute(@StringRes title: Int) {
    OnBoarding(title = R.string.on_boarding),
    SignIn(title = R.string.sign_in),
    SignUp(title = R.string.sign_up),
}