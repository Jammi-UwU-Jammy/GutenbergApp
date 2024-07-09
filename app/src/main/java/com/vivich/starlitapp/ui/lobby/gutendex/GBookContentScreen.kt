package com.vivich.starlitapp.ui.lobby.gutendex

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BookContentScreen(
    modifier: Modifier = Modifier
){
    Scaffold(
        topBar = {},
        bottomBar = { GBookContentBottomBar() },
    ){ paddings ->
        Column(
            modifier = Modifier.padding(paddings)
        ){
            Text(text = "Book Content Screen")
        }
    }
}


@Preview
@Composable
private fun ScreenPreview(modifier: Modifier = Modifier) {
    BookContentScreen()
}