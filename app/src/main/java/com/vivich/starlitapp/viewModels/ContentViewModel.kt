package com.vivich.starlitapp.viewModels

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.vivich.starlitapp.models.Gutenberg.GBook
import com.vivich.starlitapp.models.Gutenberg.ParserProvider
import com.vivich.starlitapp.models.ParsedData.BookHrefLinks
import com.vivich.starlitapp.models.ParsedData.ChapterData
import com.vivich.starlitapp.models.ParsedData.HrefTable
import com.vivich.starlitapp.models.ParsedData.extractASection
import com.vivich.starlitapp.models.ParsedData.extractHrefTable
import com.vivich.starlitapp.models.ParsedData.hrefElement
import com.vivich.starlitapp.ui.theme.bookContent
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
        Log.d("ddd", "Initiated")
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
    fun GetChaptersComposable(
        onChapterClicked: ()->Unit = {},
        chapterClicked: MutableIntState
    ){
        HrefTable(
            hrefLinks = BookHrefLinks(htmlUrl = currentBookOpened.formats.html, hrefList = hrefElements),
            chapterValue = chapterClicked,
            onChapterClicked = onChapterClicked
        )
    }

    @Composable
    fun GetLoadingComposable(modifier: Modifier = Modifier) {
        if (state is RequestUiState.Loading){
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(color = ProgressIndicatorDefaults.linearColor)
            }
        }
        else{
            Text(text = "Error occurred. Try again later.")
        }
    }

    @Composable
    fun GetContentComposable(
        chapterIndex: Int,
        textSize: MutableIntState
    ){
        val data = extractASection(html = currentParsedBook, sectionId = hrefElements[chapterIndex].hrefLink)

        return data.paragraphs.forEach{ pTag ->
            Text(text = pTag, style = bookContent(textSize.intValue))
            Spacer(modifier = Modifier.height(10.dp))
        }
    }

}

sealed interface RequestUiState{
    data class Success(val htmlString: String) : RequestUiState
    data class Error (val errorString: String) : RequestUiState
    object Loading : RequestUiState
}

class GContentViewModelFactory(
    private val currentBookOpened: GBook,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GContentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GContentViewModel(currentBookOpened) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}