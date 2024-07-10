package com.vivich.starlitapp.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vivich.starlitapp.models.Gutenberg.GBook
import com.vivich.starlitapp.models.Gutenberg.Repository
import com.vivich.starlitapp.pagination.PaginationFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GBookViewModel : ViewModel(){
    private val repo = Repository()
    var state by mutableStateOf(ScreenState())
    var id by mutableIntStateOf(0)

    private val pagination = PaginationFactory(
        initialPage = state.page,
        onLoadUpdated = {
            state = state.copy(
                isLoading = it
            )
        },
        onRequest = { nextPage ->
            repo.getPopularBooks(nextPage)
        },
        getNextKey = {
            state.page + 1
        },
        onSuccess = {items, newPage ->
            state = state.copy(
                gBooks = state.gBooks + items.data,
                page = newPage,
                endReached = state.page == 2,
            )
        },
        onError = {
            state = state.copy(error = it?.localizedMessage)
        },

    )

    init {
        loadNextItems()
    }

    fun loadNextItems() {
        viewModelScope.launch {
            pagination.loadNextPage()
        }
    }

    fun getBookContentHtml(url: String){
        viewModelScope.launch {
            try {
                Log.d("ddd", "PROC: Book retrieve")
                val content = withContext(Dispatchers.IO) {
                    repo.getBookContentByUrl(url)
                }
                if (content != null) {
                    Log.d("ddd", "SUCCESS: $content")
                }

            }catch (e: Exception){
                Log.d("ddd", "ERROR: Book retrieve - $e")
                state = state.copy(
                    error = e.message
                )
            }
        }
    }
}

fun parseHtmlToText(htmlContent: String): String {
    return Jsoup.parse(htmlContent).text()
}

data class ScreenState(
    val gBooks: List<GBook> = emptyList(),
    val page:Int = 1,
    val bookDetails: GBook = GBook(),
    val endReached: Boolean = false,
    val error: String? = null,
    val isLoading: Boolean = false,
    val currentParsedBook: String = ""
)