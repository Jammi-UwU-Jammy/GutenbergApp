package com.vivich.starlitapp.ui.lobby.gutendex

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.vivich.starlitapp.BookScreens
import com.vivich.starlitapp.BookScreens.Details.withId
import com.vivich.starlitapp.LobbyNavGraph
import com.vivich.starlitapp.models.Gutenberg.GAuthor
import com.vivich.starlitapp.models.Gutenberg.GBook
import com.vivich.starlitapp.ui.shared.BookCard
import com.vivich.starlitapp.ui.shared.SmallBookImage
import com.vivich.starlitapp.ui.theme.bookIDSmall
import com.vivich.starlitapp.ui.theme.bookTitleMedium
import com.vivich.starlitapp.ui.theme.contentGrayMedium
import com.vivich.starlitapp.viewModels.GBookViewModel


@Composable
fun GutendexScreen(
    navHostController: NavHostController = rememberNavController(),
    onHomeClicked: () -> Unit = {},
    onSettingsClicked: () -> Unit = {},
    onProfileClicked: () -> Unit = {}

) {
    val currentRoute = navHostController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        topBar = {},
        bottomBar = {},
        content = {
            LobbyNavGraph(navController = navHostController, paddingValues = it)
        }
    )
}

@Composable
fun GutendexMainBody(
    paddingValues: PaddingValues,
    bookViewModel: GBookViewModel,
    navController: NavHostController,
) {
    val state = bookViewModel.state

    Column(
        modifier = Modifier.padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GutendexHeadline()
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(state.gBooks.size) {
                if (it >= state.gBooks.size - 1 && !state.endReached && !state.isLoading) {
                    bookViewModel.loadNextItems()
                }
                GBookItem(
                    gBookItem = state.gBooks[it],
                    onItemClicked = {
                        navController.navigate(BookScreens.Details.withId("$it")){
                            launchSingleTop = true
                        }
                    }
                )
            }
            item(state.isLoading) {
                if (state.isLoading) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(color = ProgressIndicatorDefaults.circularColor)
                    }
                }
                if (!state.error.isNullOrEmpty()) {
                    Toast.makeText(LocalContext.current, state.error, Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}

@Composable
private fun GBookItem(
    gBookItem: GBook,
    onItemClicked: () -> Unit = {}
) {
    val authors = gBookItem.getAuthorsNames()
    BookCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 5.dp)
            .clickable {
                onItemClicked()
            }
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            SmallBookImage(
                painter=rememberAsyncImagePainter(model = gBookItem.formats.imageUrl)
            )
            BookDetails(
                modifier = Modifier.weight(1f),
                name = gBookItem.title,
                author = authors
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "")
            }
        }
    }

}

@Composable
private fun GutendexHeadline() {
    Column(
        modifier = Modifier.padding(vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Gutenberg",
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "Collection of most popular books by Project Gutenberg.",
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
    val mod = Modifier.padding(vertical = 2.dp)
    Column(
        modifier=modifier.padding(horizontal = 10.dp).fillMaxWidth()
    ){
        Text( modifier = mod, text = name, style = bookTitleMedium)
        Text(modifier = mod, text = author, style = contentGrayMedium)
        Spacer(modifier = mod.height(10.dp))
        Text(modifier = mod, text = "Gutenberg ID: 123", style = bookIDSmall)
    }
}

@Preview
@Composable
private fun GutendexScreenPreview() {
//    GutendexHeadline()
    val mockBooks = listOf(GBook(
        title = "Reading is a very good habit that one needs to develop in life",
        authors = listOf(GAuthor(name = "Mock author, Mock author, Mock author")))
    )
    GBookItem(gBookItem = mockBooks[0])
}