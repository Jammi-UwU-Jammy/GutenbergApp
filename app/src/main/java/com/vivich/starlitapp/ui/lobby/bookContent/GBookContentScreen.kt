package com.vivich.starlitapp.ui.lobby.bookContent

import android.util.Log
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
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
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
import com.vivich.starlitapp.viewModels.GBookViewModel
import com.vivich.starlitapp.viewModels.GContentViewModel
import com.vivich.starlitapp.viewModels.RequestUiState

@Composable
fun BookContentScreen(
    modifier: Modifier = Modifier,
    simpleModel: GContentViewModel,
    gBook: GBook = GBook(),
    onReturn: () -> Unit = {}
){
    val fontSize = remember {mutableIntStateOf(14) }
    val scrollState = rememberScrollState()
    val tabEnabled = remember { mutableIntStateOf(0) }
    var currentLoadedChapter = remember { mutableIntStateOf(0)}

    Scaffold(
        topBar = {
            BookContentTop(book =  gBook, onReturn = onReturn){
                Row(
                    modifier = Modifier.fillMaxWidth().height(50.dp)
                ){
                    NavigationBarItem(
                        selected = tabEnabled.intValue == 0,
                        onClick = { tabEnabled.intValue = 0 },
                        icon = {
                            Text(text = "Chapters")
                        }
                    )
                    NavigationBarItem(
                        selected = tabEnabled.intValue == 1,
                        onClick = { tabEnabled.intValue = 1 },
                        icon = {
                            Text(text = "Start Reading")
                        }
                    )
                }
            }
        },
        bottomBar = { GBookContentBottomBar(fontSize = fontSize) },
    ) { paddings ->
        Column(
            modifier = Modifier
                .padding(paddings)
                .padding(horizontal = 15.dp )
                .verticalScroll(scrollState)
        ) {

            when(tabEnabled.intValue){
                0 -> {
                    if (simpleModel.state is RequestUiState.Success){
                        simpleModel.GetChaptersComposable(
                            chapterClicked = currentLoadedChapter,
                            onChapterClicked = {
                                tabEnabled.intValue = 1
                            }
                        )
                    }else{
                        simpleModel.GetLoadingComposable()
                    }
                }
                1 -> {
                    simpleModel.GetContentComposable(
                        chapterIndex = currentLoadedChapter.intValue,
                        textSize = fontSize
                    )
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
        simpleModel = GContentViewModel(GBook())
    )
}