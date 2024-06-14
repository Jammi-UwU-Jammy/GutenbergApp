package com.vivich.starlitapp.ui.lobby.library

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.vivich.starlitapp.R

val randomSizedPhotos = listOf(
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRIBYZKyroi6mYHAEz6jPweNHPhVtAJrjJdXA&s",
    "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4f/Ophelia_1894.jpg/800px-Ophelia_1894.jpg",
    "https://upload.wikimedia.org/wikipedia/commons/9/9c/Friedrich_Wilhelm_Theodor_Heyser_-_Ophelia.jpg",
    "https://64.media.tumblr.com/14f2f4e5ebcda52b9a8f9bd13754aae7/5c25eea1609583ad-b6/s1280x1920/d05a3843483d7a90657445de2a9d8c5b20cbaae3.jpg",
    "https://cdn.europosters.eu/image/750/art-photo/ophelia-1910-i136637.jpg"
)

@Composable
fun LibraryScreen() {
    Surface {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(200.dp),
            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            content = {
                items(randomSizedPhotos.size) { idx ->
                    AsyncImage(
//                        painter = rememberAsyncImagePainter(model = "https://upload.wikimedia.org/wikipedia/commons/9/94/John_Everett_Millais_-_Ophelia_-_Google_Art_Project.jpg")
                        model = randomSizedPhotos[idx],
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    )
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
fun LibraryScreenPreview() {
    LibraryScreen()
}