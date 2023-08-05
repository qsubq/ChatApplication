package com.example.compose_oech

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose_oech.di.DaggerAppComponent
import com.example.compose_oech.screen.signIn.SignInScreen
import com.example.sign_up.presentation.SignUpScreen
import com.example.compose_oech.ui.theme.Compose_oechTheme
import com.example.core.AppRoute
import com.example.on_boarding.onBoarding.OnBoardingViewModelFactory
import com.example.on_boarding.onBoarding.presentation.OnBoardingScreen
import com.example.on_boarding.onBoarding.presentation.OnBoardingViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    private lateinit var onBoardingViewModel: OnBoardingViewModel

    @Inject
    lateinit var onBoardingViewModelFactory: OnBoardingViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerAppComponent.builder().context(applicationContext).build().inject(this)
        onBoardingViewModel = ViewModelProvider(this, onBoardingViewModelFactory)
            .get(OnBoardingViewModel::class.java)

        setContent {
            Compose_oechTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val state = onBoardingViewModel.isAlreadySeenLiveData.observeAsState()
                    val navController = rememberNavController()

                    LaunchedEffect(key1 = true) {
                        onBoardingViewModel.isAlreadySeenOnBoarding()
                    }

                    if (state.value == true) {
                        ChatApp(navController, AppRoute.SignUp)
                    } else {
                        ChatApp(navController, AppRoute.OnBoarding)
                    }
                }
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> daggerViewModel(
    key: String? = null,
    crossinline viewModelInstanceCreator: () -> T,
): T =
    androidx.lifecycle.viewmodel.compose.viewModel(
        modelClass = T::class.java,
        key = key,
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return viewModelInstanceCreator() as T
            }
        },
    )

@Composable
fun ChatApp(
    navController: NavHostController,
    startDestination: AppRoute,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.name,
    ) {
        composable(AppRoute.OnBoarding.name) {
            val component =
                DaggerAppComponent.builder().context(LocalContext.current.applicationContext)
                    .build()

            val viewModel: OnBoardingViewModel = daggerViewModel {
                component.getViewModel()
            }

            OnBoardingScreen(
                navController,
                viewModel,
            )
        }
        composable(AppRoute.SignUp.name) { SignUpScreen() }
        composable(AppRoute.SignIn.name) { SignInScreen(navController) }
    }
}
