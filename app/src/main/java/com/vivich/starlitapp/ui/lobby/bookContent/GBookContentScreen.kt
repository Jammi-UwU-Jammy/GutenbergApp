package com.vivich.starlitapp.ui.lobby.bookContent

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vivich.starlitapp.R
import com.vivich.starlitapp.models.Gutenberg.GBook
import com.vivich.starlitapp.ui.theme.bookContent
import com.vivich.starlitapp.viewModels.GBookViewModel

@Composable
fun BookContentScreen(
    modifier: Modifier = Modifier,
    viewModel: GBookViewModel,
    gBook: GBook = GBook()
){
    val fontSize = remember {mutableIntStateOf(14) }
    val scrollState = rememberScrollState()


    Scaffold(
        topBar = { BookContentTop()},
        bottomBar = { GBookContentBottomBar(fontSize = fontSize) },
    ){ paddings->
        Column(
            modifier = Modifier
                .padding(paddings)
                .padding(15.dp)
                .verticalScroll(scrollState)
        ){
            BookText(fontSize = fontSize.intValue, text = viewModel.state.currentParsedBook)
        }
    }
}

@Composable
private fun BookText(
    modifier: Modifier = Modifier,
    fontSize: Int,
    text: String = stringResource(id = R.string.Book_text_placeholder)
) {
    Text(text = text,  style = bookContent(fontSize))
}


@Preview
@Composable
private fun ScreenPreview(modifier: Modifier = Modifier) {
    BookContentScreen(viewModel = GBookViewModel())
}