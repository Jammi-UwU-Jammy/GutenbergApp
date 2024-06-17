package com.vivich.starlitapp.ui.lobby.library

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vivich.starlitapp.models.Gutenberg.GBook
import com.vivich.starlitapp.ui.shared.BookCard
import com.vivich.starlitapp.ui.shared.SmallBookImage
import com.vivich.starlitapp.viewModels.GBookViewModel

val randomSizedPhotos = listOf(
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRIBYZKyroi6mYHAEz6jPweNHPhVtAJrjJdXA&s",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4f/Ophelia_1894.jpg/800px-Ophelia_1894.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/9/9c/Friedrich_Wilhelm_Theodor_Heyser_-_Ophelia.jpg",
    "https://64.media.tumblr.com/14f2f4e5ebcda52b9a8f9bd13754aae7/5c25eea1609583ad-b6/s1280x1920/d05a3843483d7a90657445de2a9d8c5b20cbaae3.jpg",
    "https://cdn.europosters.eu/image/750/art-photo/ophelia-1910-i136637.jpg"
)

@Composable
fun LibraryScreen() {

    val bookViewModel = viewModel<GBookViewModel>()
    val state = bookViewModel.state

    Surface {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            LibraryHeadline()
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ){
                items(state.gBooks.size){
                    if (it >= state.gBooks.size-1 && !state.endReached && !state.isLoading){
                        bookViewModel.loadNextItems()
                    }
                    GBookItem(
                        bookIndex = it,
                        bookList = state.gBooks
                    )
                }
                item(state.isLoading){
                    if (state.isLoading){
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator(color = ProgressIndicatorDefaults.circularColor)
                        }
                    }
                    if (!state.error.isNullOrEmpty()){
                        Toast.makeText(LocalContext.current, state.error, Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }

    }
}

@Composable
private fun GBookItem(
    bookIndex: Int = 0,
    bookList: List<GBook>
) {
    BookCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 5.dp),
    ) {
        Box(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
            ) {
                SmallBookImage()
                Spacer(modifier = Modifier.width(20.dp))
                BookDetails(
                    name = bookList[bookIndex].title,
                    author = "???"
                )
            }
            ReadButton(modifier = Modifier.align(Alignment.CenterEnd))
        }
    }
}

@Composable
private fun LibraryHeadline() {
    Column(
        modifier = Modifier.padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Library",
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "Collection of books saved by you.",
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
private fun ReadButton(
    modifier: Modifier = Modifier
) {
    IconButton(
        modifier = modifier,
        onClick = { /*TODO*/ }
    ) {
        Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "")
    }
}


@Composable
private fun BookDetails(
    modifier: Modifier = Modifier,
    name: String = "",
    author: String = ""
) {
    Column(
        modifier = modifier
    ){
        Text(text = name)
        Text(text = author)
        Text(text = "Category/to be implemented")
    }
}

@Preview
@Composable
fun LibraryScreenPreview() {
    LibraryScreen()
}