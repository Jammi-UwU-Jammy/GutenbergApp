package com.vivich.starlitapp.models.Gutenberg

import com.vivich.starlitapp.domain.iGutendexAPI
import com.vivich.starlitapp.domain.IParserSerivce
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitInstance{
    val baseUrl = "https://gutendex.com/"

    val api: iGutendexAPI by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(iGutendexAPI::class.java)
    }

    fun fetchScrapedContent(url: String): String? {
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute()
        return if (response.isSuccessful) {
            response.body?.string()
        } else {
            null
        }
    }
}

class Parser {
    private val parser: IParserSerivce by lazy {
        Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl("https://dummyurl.com/")
            .build()
            .create(IParserSerivce::class.java)
    }

    suspend fun parseHTMLByUrl(url: String): Response<String> {
        val result = parser.fetchBookHTML(url)

        return result
    }
}

object ParserProvider{
    private val parser: IParserSerivce by lazy {
        Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl("https://dummyurl.com/")
            .build()
            .create(IParserSerivce::class.java)
    }
    suspend fun parseHTMLByUrl(url: String): Response<String> {
        val result = parser.fetchBookHTML(url)
        return result
    }
}

class Repository{

    suspend fun getPopularBooks(page: Int): Response<GBookList>{
        return RetrofitInstance.api.getBooks(page)
    }

    suspend fun getBookContentByUrl(url: String): String? {
        return RetrofitInstance.fetchScrapedContent(url)
    }

}


