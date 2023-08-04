package com.example.compose_oech

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.core.AppRoute
import com.example.on_boarding.onBoarding.presentation.OnBoardingViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

private lateinit var navController: TestNavHostController

private lateinit var viewModel: OnBoardingViewModel

@RunWith(MockitoJUnitRunner::class)
class OnBoardingTest {
    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setupOnBoardingNavHost() {
        rule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            ChatApp(navController, AppRoute.OnBoarding)
        }
    }

    @Test
    fun signUpBtn_exists() {
        with(rule) {
            onNodeWithTag("NextBtn").performClick()
            onNodeWithTag("NextBtn").performClick()
            onNodeWithTag("SignUpBtn").assertExists()
            onNodeWithTag("SkipBtn").assertDoesNotExist()
            onNodeWithTag("NextBtn").assertDoesNotExist()
        }
    }

    @Test
    fun onBoardingNavHost_clickSignUpBtn_navigatesToSignUpScreen() {
        with(rule) {
            onNodeWithTag("NextBtn").performClick()
            onNodeWithTag("NextBtn").performClick()
            onNodeWithTag("SignUpBtn").performClick()
            assertEquals(
                AppRoute.SignUp.name,
                navController.currentBackStackEntry?.destination?.route,
            )
        }
    }

    @Test
    fun onBoardingNavHost_clickSkipBtn_navigatesToSignUpScreen() {
        with(rule) {
            onNodeWithTag("SkipBtn").performClick()
            assertEquals(
                AppRoute.SignUp.name,
                navController.currentBackStackEntry?.destination?.route,
            )
        }
    }

    @Test
    fun onBoardingNavHost_clickSignInText_navigatesToSignUpScreen() {
        with(rule) {
            onNodeWithTag("NextBtn").performClick()
            onNodeWithTag("NextBtn").performClick()
            onNodeWithTag("SpanTextSignIn").performClick()
            assertEquals(
                AppRoute.SignIn.name,
                navController.currentBackStackEntry?.destination?.route,
            )
        }
    }
}
