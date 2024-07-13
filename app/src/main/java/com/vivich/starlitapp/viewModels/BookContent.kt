package com.vivich.starlitapp.viewModels

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vivich.starlitapp.models.Gutenberg.GBook
import com.vivich.starlitapp.models.Gutenberg.ParserProvider
import com.vivich.starlitapp.models.ParsedData.ChapterData
import com.vivich.starlitapp.models.ParsedData.hrefElement
import kotlinx.coroutines.launch


class GContentViewModel(
    private val hrefElements: List<hrefElement> = emptyList(),
    private val chapters: List<ChapterData> = emptyList(),
    private val page:Int = 0,

    val currentParsedBook: String = "",
    val currentBookOpened: GBook = GBook(title = "Non-available")
) : ViewModel(){

    var state : RequestUiState by mutableStateOf(RequestUiState.Loading)
        private set

    private fun fetchHtmlContent(url: String) {
        viewModelScope.launch {
            try {
                //TODO:
                val response = ParserProvider.parseHTMLByUrl(url)
                if (response.isSuccessful){
                    state = RequestUiState.Success(response.body().toString())
                }else{
                    state = RequestUiState.Error(response.errorBody().toString())
                }
            }catch (e: Exception){
                Log.d("ddd", "ERROR: HTML retrieve - $e")

            }
        }
    }

}

sealed interface RequestUiState{
    data class Success(val htmlString: String) : RequestUiState
    data class Error (val errorString: String) : RequestUiState
    object Loading : RequestUiState
}
