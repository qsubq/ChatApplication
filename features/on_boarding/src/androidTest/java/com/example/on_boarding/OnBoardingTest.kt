package com.example.on_boarding

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import com.example.on_boarding.onBoarding.presentation.OnBoardingScreen
import org.junit.Rule
import org.junit.Test

class OnBoardingTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun signUpBtn_exists() {
        rule.setContent { OnBoardingScreen(navController = rememberNavController()) }

        with(rule) {
            onNodeWithTag("NextBtn").performClick()
            onNodeWithTag("NextBtn").performClick()
            onNodeWithTag("SignUpBtn").assertExists()
            onNodeWithTag("SkipBtn").assertDoesNotExist()
            onNodeWithTag("NextBtn").assertDoesNotExist()
        }
    }

    @Test
    fun transitions() {

    }
}
