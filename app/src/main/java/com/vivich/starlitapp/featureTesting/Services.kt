package com.vivich.starlitapp.featureTesting

import retrofit2.http.GET


object Constants{
    const val PAGE_SIZE: Int = 20
    object PostAPI{
        const val URL: String = "http://localhost:8080/"
        const val NAME = "post_api"
    }
    object SecondaryAPI{
        const val URL: String = "http://localhost:8080/"
        const val NAME = "another_api"
    }
}

// Post.kt
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    // Add other fields as needed
)

// Page.kt
data class Page<T>(
    val content: List<T>,
    val totalPages: Int,
    val totalElements: Int,
    val last: Boolean,
    val first: Boolean,
    val number: Int,
    val size: Int
)



interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}

