package com.vivich.starlitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vivich.starlitapp.data.Post
import com.vivich.starlitapp.data.PostRepository
import com.vivich.starlitapp.data.PostService
import com.vivich.starlitapp.data.PostViewModel
import com.vivich.starlitapp.data.PostViewModelFactory
import com.vivich.starlitapp.ui.theme.StarlitAppTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val postService = Retrofit.Builder()
            .baseUrl("http://localhost:8000/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostService::class.java)

        val repository = PostRepository(postService)

        setContent {
            StarlitAppTheme {
                // A surface container using the 'background' color from the theme
//                LoginScreen()
//                AdSlideSection()
                PostListScreen(repository = repository)
            }
        }
    }
}

@Composable
fun PostListScreen(repository: PostRepository) {
    val viewModel: PostViewModel = viewModel(factory = PostViewModelFactory(repository))
    val posts by viewModel.posts

    LazyColumn {
        items(posts) { post ->
            PostItem(post)
        }
    }
}

@Composable
fun PostItem(post: Post) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(text = post.title, style = MaterialTheme.typography.headlineMedium)
        Text(text = post.description, style = MaterialTheme.typography.labelMedium)
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StarlitAppTheme {

    }
}