package com.example.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun CustomDialog(openDialogCustom: MutableState<Boolean>, title: String, description: String) {
    Dialog(onDismissRequest = { openDialogCustom.value = false }) {
        CustomDialogUILayout(
            openDialogCustom = openDialogCustom,
            title = title,
            description = description,
        )
    }
}

@Composable
fun CustomDialogUILayout(
    modifier: Modifier = Modifier,
    openDialogCustom: MutableState<Boolean>,
    title: String,
    description: String,
) {
    if (openDialogCustom.value) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 10.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp,
            ),
        ) {
            Column(
                modifier
                    .background(Color.White),
            ) {
                Text(
                    title,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    fontSize = 22.sp,
                    modifier = Modifier.padding(
                        top = 10.dp,
                        start = 18.dp,
                        end = 18.dp,
                    ),
                    maxLines = 5,
                    textAlign = TextAlign.Left,
                )

                Text(
                    description,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(
                        top = 16.dp,
                        start = 18.dp,
                        end = 18.dp,
                    ),
                    maxLines = 5,
                    textAlign = TextAlign.Left,
                )

                Text(
                    "OK",
                    fontWeight = FontWeight.Normal,
                    color = Color.Blue,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(
                        top = 20.dp,
                        bottom = 10.dp,
                        end = 18.dp,
                    ).align(Alignment.End).clickable {
                        openDialogCustom.value = false
                    },
                    textAlign = TextAlign.Left,
                )
            }
        }
    }
}
