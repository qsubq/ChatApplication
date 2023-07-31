package com.example.compose_oech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose_oech.screen.signIn.SignInScreen
import com.example.compose_oech.screen.signUp.SignUpScreen
import com.example.compose_oech.ui.theme.Compose_oechTheme
import com.example.on_boarding.onBoarding.presentation.OnBoardingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_oechTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "onBoarding") {
                        composable("onBoarding") { OnBoardingScreen(navController) }
                        composable("signUp") { SignUpScreen(navController) }
                        composable("signIn") { SignInScreen(navController) }
                    }
                }
            }
        }
    }
}
