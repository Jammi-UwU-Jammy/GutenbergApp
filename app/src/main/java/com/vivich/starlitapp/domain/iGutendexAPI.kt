package com.vivich.starlitapp.domain

import com.vivich.starlitapp.models.Gutenberg.GBook
import com.vivich.starlitapp.models.Gutenberg.GBookList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface iGutendexAPI {
    @GET("books")
    suspend fun getBooks(
        @Query("page")page: Int
    ): Response<GBookList>

    @GET ("books/{id}")
    suspend fun getBookDetails(
        @Query ("id")id: Int
    ) : Response<GBook>
}