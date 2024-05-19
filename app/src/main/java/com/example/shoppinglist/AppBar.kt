package com.example.shoppinglist

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppBarView(
    title: String,
    onBackNavClick: () -> Unit = {}
) {
    val navigationIcon: (@Composable () -> Unit)? =
        if(!title.contains("Shopping List")) {
            {
                IconButton(onClick = { onBackNavClick() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        tint = Color.Black,
                        contentDescription = null
                    )
                }
            }
        }
        else {
            null
        }


    TopAppBar(
        title = {
            Text(
                text = title,
                color = colorResource(id = R.color.black),
                modifier = Modifier
                    .padding(start = 4.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.W800
            )
        },
        elevation = 3.dp,
        backgroundColor = colorResource(id = R.color.white),
        navigationIcon = navigationIcon
    )
}