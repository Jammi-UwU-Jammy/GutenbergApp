package com.vivich.starlitapp.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query


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
    val id: Int,
    val title: String,
    val description: String,
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



interface PostService {
    @GET("/todos")
    suspend fun get(
        @Query("page") page: Int,
        @Query("size") size: Int = Constants.PAGE_SIZE
    ): Response<Page<Post>>

    @GET("/todos/{id}")
    suspend fun get(@Path("id") id: Int): Response<Post>

    @POST("/posts")
    suspend fun post(@Body post: Post): Response<Post>

    @PUT("/posts/{id}")
    suspend fun put(
        @Path("id") id: Int,
        @Body request: Post
    ): Response<Post>

    @PATCH("/posts/{id}")
    suspend fun patch(
        @Path("id") id: Int,
        @Query("status") status: Boolean
    ): Response<Unit>

    @DELETE("/posts/{id}")
    suspend fun delete(@Path("id") id: Int): Response<Unit>
}