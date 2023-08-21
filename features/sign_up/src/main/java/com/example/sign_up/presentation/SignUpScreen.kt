package com.example.sign_up.presentation

import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
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
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.example.core.AppRoute
import com.example.core.R
import com.example.core.state.UiState
import com.example.core.ui.CustomDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController, viewModel: SignUpViewModel) {
    Log.e("Sign up", "Sign up screen is initialized")

    val localUriHandler = LocalUriHandler.current

    val fullNameFieldState = rememberSaveable { mutableStateOf("") }
    val phoneNumberFieldState = rememberSaveable { mutableStateOf("") }
    val emailAddressFieldState = rememberSaveable { mutableStateOf("") }
    val passwordFieldState = rememberSaveable { mutableStateOf("") }

    val passwordVisibleState = rememberSaveable { mutableStateOf(false) }

    val isErrorPassword = rememberSaveable { mutableStateOf(false) }
    val isErrorEmail = rememberSaveable { mutableStateOf(false) }

    val checkBoxState = rememberSaveable { mutableStateOf(false) }
    val isBtnEnabled = rememberSaveable { mutableStateOf(false) }

    val uiState = viewModel.uiState.collectAsState()

    val progressBarVisible = rememberSaveable { mutableStateOf(false) }
    val openDialogState = rememberSaveable { mutableStateOf(true) }

    val annotatedTermsAndPolicy =
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = colorResource(id = R.color.gray_80),
                    fontSize = 12.sp,
                ),
            ) {
                append("By ticking this box, you agree to our ")
            }
            withStyle(
                style = SpanStyle(
                    color = colorResource(id = R.color.yellow_100),
                    fontSize = 12.sp,
                ),
            ) {
                pushStringAnnotation(tag = "terms", annotation = "https://google.com/terms")
                append("Terms and conditions and private policy")
                pop()
            }
        }

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
                pushStringAnnotation(tag = "Sign in", annotation = "")
                append("Sign in")
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
            viewModel.goToSignInScreen()
            navController.navigate(AppRoute.SignIn.name)
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
                .padding(start = 24.dp, top = 36.dp, end = 24.dp),
        ) {
            Text(
                text = "Create an account",
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                color = Color(0xFF3A3A3A),
            )

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Complete the sign up process to get started",
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = Color(0xffA7A7A7),
            )

            Text(
                modifier = Modifier.padding(top = 38.dp),
                text = "Full Name",
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
                value = fullNameFieldState.value,
                singleLine = true,
                onValueChange = {
                    fullNameFieldState.value = it

                    if (fullNameFieldState.value.isNotEmpty() &&
                        phoneNumberFieldState.value.isNotEmpty() &&
                        emailAddressFieldState.value.isNotEmpty() &&
                        passwordFieldState.value.isNotEmpty() &&
                        !isErrorEmail.value &&
                        !isErrorPassword.value &&
                        checkBoxState.value
                    ) {
                        isBtnEnabled.value = true
                    }
                },
                placeholder = {
                    Text("Ken Nwaeze", color = colorResource(id = R.color.gray_40))
                },
            )

            Text(
                modifier = Modifier.padding(top = 24.dp),
                text = "Phone Number",
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
                value = phoneNumberFieldState.value,
                singleLine = true,
                onValueChange = {
                    if (it.isDigitsOnly()) {
                        phoneNumberFieldState.value = it

                        if (fullNameFieldState.value.isNotEmpty() &&
                            phoneNumberFieldState.value.isNotEmpty() &&
                            emailAddressFieldState.value.isNotEmpty() &&
                            passwordFieldState.value.isNotEmpty() &&
                            !isErrorEmail.value &&
                            !isErrorPassword.value &&
                            checkBoxState.value
                        ) {
                            isBtnEnabled.value = true
                        }
                    }
                },
                placeholder = {
                    Text("070764480655", color = colorResource(id = R.color.gray_40))
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )

            Text(
                modifier = Modifier.padding(top = 24.dp),
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
                value = emailAddressFieldState.value,
                singleLine = true,
                onValueChange = {
                    emailAddressFieldState.value = it

                    if (it.isNotEmpty()) {
                        isErrorEmail.value = !Patterns.EMAIL_ADDRESS.matcher(it).matches()
                    } else {
                        isErrorEmail.value = false
                    }

                    if (fullNameFieldState.value.isNotEmpty() &&
                        phoneNumberFieldState.value.isNotEmpty() &&
                        emailAddressFieldState.value.isNotEmpty() &&
                        passwordFieldState.value.isNotEmpty() &&
                        !isErrorEmail.value &&
                        !isErrorPassword.value &&
                        checkBoxState.value
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

                    if (fullNameFieldState.value.isNotEmpty() &&
                        phoneNumberFieldState.value.isNotEmpty() &&
                        emailAddressFieldState.value.isNotEmpty() &&
                        passwordFieldState.value.isNotEmpty() &&
                        !isErrorEmail.value &&
                        !isErrorPassword.value &&
                        checkBoxState.value
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
                Checkbox(
                    modifier = Modifier
                        .scale(0.85f)
                        .align(Alignment.CenterStart),
                    checked = checkBoxState.value,
                    onCheckedChange = {
                        checkBoxState.value = it

                        if (fullNameFieldState.value.isNotEmpty() &&
                            phoneNumberFieldState.value.isNotEmpty() &&
                            emailAddressFieldState.value.isNotEmpty() &&
                            passwordFieldState.value.isNotEmpty() &&
                            !isErrorEmail.value &&
                            !isErrorPassword.value &&
                            it
                        ) {
                            isBtnEnabled.value = true
                        }
                    },
                    colors = CheckboxDefaults.colors(
                        uncheckedColor = colorResource(
                            id = R.color.blue_100,
                        ),
                    ),
                )

                ClickableText(
                    modifier = Modifier
                        .padding(start = 12.dp, top = 8.dp)
                        .align(Alignment.Center)
                        .width(270.dp),
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                    ),
                    onClick = { offset ->
                        annotatedTermsAndPolicy.getStringAnnotations(
                            tag = "terms",
                            start = offset,
                            end = offset,
                        ).firstOrNull()?.let {
                            localUriHandler.openUri("https://policies.google.com/terms?hl=en")
                        }
                    },

                    text = annotatedTermsAndPolicy,
                )
            }

            Button(
                modifier = Modifier
                    .padding(top = 64.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = {
                    viewModel.signUpUser(
                        fullNameFieldState.value,
                        phoneNumberFieldState.value.toInt(),
                        emailAddressFieldState.value,
                        passwordFieldState.value,
                    )
                },
                shape = RoundedCornerShape(8.dp),
                enabled = isBtnEnabled.value,
            ) {
                Text(
                    text = "Sign Up",
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
                        tag = "Sign in",
                        start = offset,
                        end = offset,
                    ).firstOrNull()?.let {
                        navController.navigate(AppRoute.SignIn.name)
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
