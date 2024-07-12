package com.vivich.starlitapp.ui.lobby.bookContent

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vivich.starlitapp.R
import com.vivich.starlitapp.models.Gutenberg.GBook
import com.vivich.starlitapp.ui.theme.bookContent
import com.vivich.starlitapp.viewModels.GBookLoaderViewModel
import com.vivich.starlitapp.viewModels.GBookViewModel

@Composable
fun BookContentScreen(
    modifier: Modifier = Modifier,
    testModel: GBookLoaderViewModel,
    viewModel: GBookViewModel,
    gBook: GBook = GBook(),
    onReturn: () -> Unit = {}
){
    val fontSize = remember {mutableIntStateOf(14) }
    val scrollState = rememberScrollState()
    val tabEnabled = remember { mutableIntStateOf(0) }
    val text = remember { mutableStateOf("Start Reading")}

    Scaffold(
        topBar = { BookContentTop(book =  gBook, onReturn = onReturn)},
        bottomBar = { GBookContentBottomBar(fontSize = fontSize) },
    ) { paddings ->

        Column(
            modifier = Modifier
                .padding(paddings)
                .padding(15.dp)
                .verticalScroll(scrollState)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
            ){
                IconButton(
                    enabled = tabEnabled.intValue == 0,
                    modifier = Modifier.fillMaxWidth(.5f),
                    onClick = { tabEnabled.intValue = 0 }
                ) {
                    Text(text = "Chapters")
                }
                IconButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { tabEnabled.intValue = 1 },
                    enabled = tabEnabled.intValue == 1,
                ){
                    Text(text = text.value)
                }
            }
            when(tabEnabled.intValue){
                0 -> {
                    testModel.state.myComposable()
                }
                1 -> {
                    
                }
            }
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
    BookContentScreen(
        viewModel = GBookViewModel(),
        testModel = GBookLoaderViewModel(
            currentBook = GBook(title = "Preview Test Book")
        )
    )
}