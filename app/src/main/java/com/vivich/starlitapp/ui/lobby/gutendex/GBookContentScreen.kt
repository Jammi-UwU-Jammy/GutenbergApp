package com.vivich.starlitapp.ui.lobby.gutendex

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.vivich.starlitapp.viewModels.GBookViewModel

@Composable
fun BookContentScreen(
    modifier: Modifier = Modifier,
    viewModel: GBookViewModel
){
    val lightness = remember { mutableFloatStateOf(1f) }
    val fontSize = remember {mutableIntStateOf(14) }

    Scaffold(
        containerColor = Color.hsl(0f, 0f, lightness.floatValue, 1f),
        topBar = {},
        bottomBar = { GBookContentBottomBar(fontSize = fontSize) },
    ){ paddings ->
        Column(
//            modifier = Modifier
//                .padding(paddings)
        ){
            Text(text = "Book Content Screen", fontSize = fontSize.intValue.sp)
        }
    }
}


@Preview
@Composable
private fun ScreenPreview(modifier: Modifier = Modifier) {
    BookContentScreen(viewModel = GBookViewModel())
}