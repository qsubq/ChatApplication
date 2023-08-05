package com.example.sign_up.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SignUpScreen() {
    val fullNameFieldState = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(start = 24.dp, top = 36.dp)) {
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
            color = Color(0xffA7A7A7),
        )

        OutlinedTextField(
            modifier = Modifier.height(50.dp).fillMaxWidth().padding(top = 8.dp, end = 24.dp),
            value = fullNameFieldState.value,
            onValueChange = {
                fullNameFieldState.value = it
            },
        )
    }
}
