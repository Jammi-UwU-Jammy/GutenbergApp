package com.vivich.starlitapp.models.Gutenberg

import android.util.Log
import com.vivich.starlitapp.domain.iGutendexAPI
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance{
    val baseUrl = "https://gutendex.com/"

    val api: iGutendexAPI by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(iGutendexAPI::class.java)
    }
}

class Repository {
    suspend fun getPopularBooks(page: Int): Response<GBookList>{
        return RetrofitInstance.api.getBooks(page)
    }
    suspend fun getBookDetailsById(id: Int): Response<GBook>{
        return RetrofitInstance.api.getBookDetails(id=id)
    }
}