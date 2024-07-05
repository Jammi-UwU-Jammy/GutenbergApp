package com.vivich.starlitapp.ui.lobby.gutendex

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vivich.starlitapp.ui.theme.contentGraySmall

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookContentTop(
    modifier: Modifier = Modifier
){
    TopAppBar(
        navigationIcon = {
            Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription ="")
        },
        title = {
            Text(
                modifier = modifier
                    .fillMaxWidth(1f)
                    .padding(horizontal = 10.dp),
                text = "AAA",
                style = contentGraySmall,
                textAlign = TextAlign.Center
            )
        }
    )
}

@Preview
@Composable
private fun TopNavPreview(modifier: Modifier = Modifier) {
    BookContentTop()
}