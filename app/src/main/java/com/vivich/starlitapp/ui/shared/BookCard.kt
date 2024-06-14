package com.vivich.starlitapp.ui.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun BookCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    return ElevatedCard(
        modifier = modifier,
//        colors = CardDefaults.elevatedCardColors(
//            containerColor = Color.hsl(.5f, .5f, .5f, .1f),
//            contentColor = Color.Black
//        ),
    ){
        content()
    }
}