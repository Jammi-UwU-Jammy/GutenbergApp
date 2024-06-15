package com.vivich.starlitapp.data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostApi {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.PostAPI.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val instance: PostService by lazy {
        retrofit.create(PostService::class.java)
    }
}

private fun provideRetrofitInstance(
    url: String
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}


class PostRepository(private val postService: PostService) {
    suspend fun getPosts(page: Int, size: Int): List<Post>? {
        val response = postService.get(page, size)

        Log.d("ERRRORRRR", response.body().toString())

        return if (response.isSuccessful) {
            response.body()?.content
        } else {
            listOf(
                Post(
                    id = 0,
                    title = "No posts",
                    description = "No posts"
                )
            )
        }
    }
}
