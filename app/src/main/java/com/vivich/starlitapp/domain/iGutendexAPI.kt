package com.vivich.starlitapp.domain

import com.vivich.starlitapp.models.Gutenberg.GBookList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface iGutendexAPI {
    @GET("books")
    suspend fun getBooks(
        @Query("page")page: Int
    ): Response<GBookList>

}

interface IParserSerivce{
    @GET
    suspend fun fetchBookHTML(@Url url: String
    ) : Response<String>

    @GET
    suspend fun fetchBookPlain(@Url url: String
    ) : Response<String>
}