package com.vivich.starlitapp.viewModels

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.vivich.starlitapp.models.Gutenberg.GBook
import com.vivich.starlitapp.models.Gutenberg.ParserProvider
import com.vivich.starlitapp.models.ParsedData.BookHrefLinks
import com.vivich.starlitapp.models.ParsedData.ChapterContent
import com.vivich.starlitapp.models.ParsedData.ChapterData
import com.vivich.starlitapp.models.ParsedData.HrefTable
import com.vivich.starlitapp.models.ParsedData.extractHrefTable
import com.vivich.starlitapp.models.ParsedData.hrefElement
import kotlinx.coroutines.launch


class GContentViewModel(
    val currentBookOpened: GBook,
    private var currentParsedBook: String = "",

    var hrefElements: List<hrefElement> = emptyList(),
    var chapters: List<ChapterData> = emptyList(),
    private val page:Int = 0,

    ) : ViewModel(){

    var state : RequestUiState by mutableStateOf(RequestUiState.Loading)
        private set

    init{
        Log.d("ccc", "Initiated")
        this.fetchHtmlContent(currentBookOpened.formats.html)
    }

    private fun fetchHtmlContent(url: String) {
        viewModelScope.launch {
            state = RequestUiState.Loading
            try {
                //TODO:
                val response = ParserProvider.parseHTMLByUrl(url)
                if (response.isSuccessful){
                    state = RequestUiState.Success(response.body().toString())
                    currentParsedBook = response.body().toString()
                    hrefElements = extractHrefTable(response.body().toString())
                }else{
                    state = RequestUiState.Error(response.errorBody().toString())
                }
            }catch (e: Exception){
                Log.d("ddd", "ERROR: HTML retrieve - $e")
            }
        }
    }

    @Composable
    fun GetChaptersComposable(){

        Text(text = "Chapters")

//        HrefTable(
//            hrefLinks = BookHrefLinks(htmlUrl = currentBookOpened.formats.html, hrefList = hrefElements),
//            html = currentParsedBook
//        )
//        ChapterContent(
//            chapterData = ChapterData(title = currentBookOpened.title)
//        )
    }

}

sealed interface RequestUiState{
    data class Success(val htmlString: String) : RequestUiState
    data class Error (val errorString: String) : RequestUiState
    object Loading : RequestUiState
}
