package com.vivich.starlitapp.ui.lobby.bookDetails

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GBookTopNav(
    modifier: Modifier = Modifier,
    onReturnClicked: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
//        colors = TopAppBarColors(
//            containerColor= Color.hsl(.6f, 1f, 1f,.6f),
//            scrolledContainerColor= Color.Black,
//            navigationIconContentColor= Color.Black,
//            titleContentColor= Color.Black,
//            actionIconContentColor= Color.Black
//        ),
        title = { Text(text = "Book Detail") },
        navigationIcon = {
            IconButton(onClick = { onReturnClicked() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "")
            }
        }
    )
}


@Preview
@Composable
private fun TopNavPreview(modifier: Modifier = Modifier) {
    GBookTopNav()
}