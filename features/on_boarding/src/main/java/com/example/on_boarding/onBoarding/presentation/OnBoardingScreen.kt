package com.example.on_boarding.onBoarding.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.core.AppRoute
import com.example.data.model.OnBoardingModel
import com.example.on_boarding.R
import java.util.LinkedList

@Composable
fun OnBoardingScreen(navController: NavController, viewModel: OnBoardingViewModel) {
    val onBoardingQueue = remember {
        LinkedList(
            mutableListOf(
                OnBoardingModel(
                    R.drawable.onboarding_drawable_1,
                    R.string.on_boarding_title_1,
                    R.string.on_boarding_description_1,
                    R.drawable.dot_indicator_1,
                ),
                OnBoardingModel(
                    R.drawable.onboarding_drawable_2,
                    R.string.on_boarding_title_2,
                    R.string.on_boarding_description_2,
                    R.drawable.dot_indicator_2,
                ),
                OnBoardingModel(
                    R.drawable.onboarding_drawable_3,
                    R.string.on_boarding_title_3,
                    R.string.on_boarding_description_3,
                    R.drawable.dot_indicator_3,
                ),
            ),
        )
    }
    val currentItem = remember { mutableStateOf(onBoardingQueue.poll()) }

    val annotatedSignIn =
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color(0xffA7A7A7),
                    fontSize = 14.sp,
                    textDecoration = TextDecoration.Underline,
                ),
            ) {
                append("Already have an account?")
            }
            withStyle(
                style = SpanStyle(
                    color = Color(0xff0560FA),
                    fontSize = 14.sp,
                ),
            ) {
                append("Sign in")
            }
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.padding(start = 22.dp, end = 22.dp).testTag("MainImg"),
            imageVector = ImageVector.vectorResource(id = currentItem.value!!.imageId),
            contentDescription = null,
        )
        Text(
            modifier = Modifier.padding(top = 67.dp).testTag("TitleText"),
            text = stringResource(id = currentItem.value!!.title),
            color = Color(0xFF0560FA),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )

        Text(
            modifier = Modifier.padding(top = 16.dp).testTag("DescText"),
            text = stringResource(id = currentItem.value!!.desc),
            fontSize = 16.sp,
            color = Color(0xFF3A3A3A),
            textAlign = TextAlign.Center,
        )

        Image(
            modifier = Modifier.padding(top = 60.dp),
            imageVector = ImageVector.vectorResource(id = currentItem.value!!.dotsId),
            contentDescription = null,
        )

        if (onBoardingQueue.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        modifier = Modifier
                            .testTag("SignUpBtn")
                            .fillMaxWidth()
                            .padding(start = 24.dp, end = 24.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0560FA)),
                        shape = RoundedCornerShape(6.dp),
                        onClick = {
                            viewModel.setFlagSeenOnBoarding()
                            navController.navigate(AppRoute.SignUp.name) {
                                popUpTo(AppRoute.OnBoarding.name) {
                                    inclusive = true
                                }
                            }
                        },
                    ) {
                        Text(text = "Sign Up", color = Color(0xFFFFFFFF))
                    }
                }
                Text(
                    modifier = Modifier.padding(bottom = 44.dp, top = 2.dp)
                        .clickable {
                            navController.navigate(AppRoute.SignIn.name) {
                                popUpTo(AppRoute.OnBoarding.name) {
                                    inclusive = true
                                }
                            }
                        }.testTag("SpanTextSignIn"),
                    text = annotatedSignIn,
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .fillMaxSize(),
            ) {
                OutlinedButton(
                    modifier = Modifier
                        .testTag("SkipBtn")
                        .align(Alignment.BottomStart)
                        .padding(start = 24.dp),
                    onClick = {
                        viewModel.setFlagSeenOnBoarding()
                        navController.navigate(AppRoute.SignUp.name) {
                            popUpTo(AppRoute.OnBoarding.name) {
                                inclusive = true
                            }
                        }
                    },
                    border = BorderStroke(1.dp, Color(0xFF0560FA)),
                    shape = RoundedCornerShape(6.dp),
                ) {
                    Text(text = "Skip", color = Color(0xFF0560FA))
                }
                Button(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .testTag("NextBtn")
                        .padding(end = 24.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0560FA)),
                    shape = RoundedCornerShape(6.dp),
                    onClick = {
                        currentItem.value = onBoardingQueue.poll()
                    },
                ) {
                    Text(text = "Next", color = Color(0xFFFFFFFF))
                }
            }
        }
    }
}
