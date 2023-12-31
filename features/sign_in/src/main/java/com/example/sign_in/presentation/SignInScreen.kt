package com.example.sign_in.presentation

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.core.AppRoute
import com.example.core.R
import com.example.core.state.UiState
import com.example.core.ui.CustomDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(navController: NavController, viewModel: SignInViewModel) {
    Log.e("Sign in", "Sign in is initialized")

    val emailTextState = remember { mutableStateOf("") }
    val passwordFieldState = remember { mutableStateOf("") }
    val passwordVisibleState = rememberSaveable { mutableStateOf(false) }
    val checkBoxState = rememberSaveable { mutableStateOf(false) }
    val isBtnEnabled = rememberSaveable { mutableStateOf(false) }

    val isErrorPassword = rememberSaveable { mutableStateOf(false) }
    val isErrorEmail = rememberSaveable { mutableStateOf(false) }

    val uiState = viewModel.uiState.collectAsState()

    val progressBarVisible = rememberSaveable { mutableStateOf(false) }
    val openDialogState = rememberSaveable { mutableStateOf(true) }

    val annotatedSignIn =
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = colorResource(id = R.color.gray_80),
                    fontSize = 12.sp,
                ),
            ) {
                append("Already have an account?")
            }
            withStyle(
                style = SpanStyle(
                    color = colorResource(id = R.color.blue_100),
                    fontSize = 12.sp,
                ),
            ) {
                pushStringAnnotation(tag = "Sign up", annotation = "")
                append("Sign up")
                pop()
            }
        }

    when (uiState.value) {
        is UiState.Loading -> {
            progressBarVisible.value = true
        }

        is UiState.Waiting -> {
            progressBarVisible.value = false
        }

        is UiState.Success -> {
            progressBarVisible.value = false
            Toast.makeText(LocalContext.current, "SignInSuccess", Toast.LENGTH_SHORT).show()
        }

        is UiState.Error -> {
            progressBarVisible.value = false

            if (openDialogState.value) {
                CustomDialog(
                    openDialogCustom = openDialogState,
                    (uiState.value as UiState.Error).errorTitle,
                    (uiState.value as UiState.Error).errorDescription,
                )
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, top = 110.dp, end = 24.dp),
        ) {
            Text(
                text = "Welcome Back",
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                color = Color(0xFF3A3A3A),
            )

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Fill in your email and password to continue",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = Color(0xffA7A7A7),
            )

            Text(
                modifier = Modifier.padding(top = 48.dp),
                text = "Email Address",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = (colorResource(id = R.color.gray_80)),
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp),
            )

            OutlinedTextField(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                value = emailTextState.value,
                singleLine = true,
                onValueChange = {
                    emailTextState.value = it

                    if (it.isNotEmpty()) {
                        isErrorEmail.value = !Patterns.EMAIL_ADDRESS.matcher(it).matches()
                    } else {
                        isErrorEmail.value = false
                    }

                    if (passwordFieldState.value.isNotEmpty() &&
                        !isErrorEmail.value &&
                        !isErrorPassword.value
                    ) {
                        isBtnEnabled.value = true
                    }
                },
                placeholder = {
                    Text("nwaezeken@gmail.com", color = colorResource(id = R.color.gray_40))
                },
                isError = isErrorEmail.value,
                supportingText = {
                    if (isErrorEmail.value) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Invalid email",
                            color = MaterialTheme.colorScheme.error,
                        )
                    }
                },
            )

            Text(
                modifier = Modifier.padding(top = 24.dp),
                text = "Password",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = (colorResource(id = R.color.gray_80)),
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp),
            )

            OutlinedTextField(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                value = passwordFieldState.value,
                onValueChange = {
                    passwordFieldState.value = it

                    if (it.isNotEmpty()) {
                        isErrorPassword.value = !it.contains("[A-Z]".toRegex()) ||
                            !it.contains("[0-9]".toRegex()) || it.length < 6
                    } else {
                        isErrorPassword.value = false
                    }

                    if (passwordFieldState.value.isNotEmpty() &&
                        !isErrorEmail.value &&
                        !isErrorPassword.value
                    ) {
                        isBtnEnabled.value = true
                    }
                },
                placeholder = {
                    Text("**********", color = colorResource(id = R.color.gray_40))
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisibleState.value) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation('*')
                },
                singleLine = true,
                trailingIcon = {
                    val image: ImageVector
                    val description: String
                    if (passwordVisibleState.value) {
                        description = "Hide password"
                        image = Icons.Filled.Visibility
                    } else {
                        description = "Show password"
                        image = Icons.Filled.VisibilityOff
                    }

                    IconButton(onClick = {
                        passwordVisibleState.value = !passwordVisibleState.value
                    }) {
                        Icon(imageVector = image, description)
                    }
                },
                isError = isErrorPassword.value,
                supportingText = {
                    if (isErrorPassword.value) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Invalid password",
                            color = MaterialTheme.colorScheme.error,
                        )
                    }
                },
            )
            Box(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.align(Alignment.CenterStart)) {
                    Checkbox(
                        modifier = Modifier
                            .scale(0.85f),
                        checked = checkBoxState.value,
                        onCheckedChange = { checkBoxState.value = it },
                        colors = CheckboxDefaults.colors(
                            uncheckedColor = colorResource(
                                id = R.color.gray_80,
                            ),
                        ),
                    )

                    Text(
                        modifier = Modifier.padding(top = 15.dp),
                        text = "Remember password",
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        color = colorResource(
                            id = R.color.gray_80,
                        ),
                    )
                }

                Text(
                    modifier = Modifier.padding(top = 15.dp).align(Alignment.TopEnd),
                    text = "Forgot Password?",
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = colorResource(
                        id = R.color.blue_100,
                    ),
                )
            }
            Button(
                modifier = Modifier
                    .padding(top = 164.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = {
                    viewModel.signInUser(
                        email = emailTextState.value,
                        password = passwordFieldState.value,
                    )
                },
                shape = RoundedCornerShape(8.dp),
                enabled = isBtnEnabled.value,
            ) {
                Text(
                    text = "Log in",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(
                        id = R.color.white,
                    ),
                )
            }

            ClickableText(
                modifier = Modifier
                    .padding(start = 12.dp, top = 5.dp)
                    .align(Alignment.CenterHorizontally)
                    .width(270.dp),
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                ),
                onClick = { offset ->
                    annotatedSignIn.getStringAnnotations(
                        tag = "Sign up",
                        start = offset,
                        end = offset,
                    ).firstOrNull()?.let {
                        navController.navigate(AppRoute.SignUp.name)
                    }
                },
                text = annotatedSignIn,
            )
        }

        if (progressBarVisible.value) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}
