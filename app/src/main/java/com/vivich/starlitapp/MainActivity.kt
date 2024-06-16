package com.vivich.starlitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.vivich.starlitapp.featureTesting.MainViewModel
import com.vivich.starlitapp.ui.theme.StarlitAppTheme
import androidx.compose.runtime.collectAsState


class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            StarlitAppTheme {
                // A surface container using the 'background' color from the theme
//                LoginScreen()
//                AdSlideSection()
                PostList(viewModel = viewModel)
            }
        }
    }
}
@Composable
fun PostList(viewModel: MainViewModel) {
    val posts by viewModel.posts.collectAsState()
    LazyColumn {
        items(posts) {
                post ->
            Text(text = post.title)
        }
    }
    DisposableEffect(Unit) {
        viewModel.getPosts()
        onDispose {}
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StarlitAppTheme {

    }
}