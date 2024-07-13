package com.vivich.starlitapp.viewModels

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vivich.starlitapp.models.Gutenberg.GBook
import com.vivich.starlitapp.models.Gutenberg.Parser
import com.vivich.starlitapp.models.Gutenberg.Repository
import com.vivich.starlitapp.models.ParsedData.BookHrefLinks
import com.vivich.starlitapp.models.ParsedData.ChapterContent
import com.vivich.starlitapp.models.ParsedData.ChapterData
import com.vivich.starlitapp.models.ParsedData.HrefTable
import com.vivich.starlitapp.models.ParsedData.extractASection
import com.vivich.starlitapp.models.ParsedData.extractHrefTable
import com.vivich.starlitapp.models.ParsedData.hrefElement
import com.vivich.starlitapp.pagination.ContentPaginationFactory
import com.vivich.starlitapp.pagination.PaginationFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class GBookViewModel : ViewModel(){
    private val repo = Repository()
//    private val

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

    fun updateCurrentOpenedBook(
        bookIndex: Int,
    ){
        val hrefLinks = BookHrefLinks(
            htmlUrl = state.currentBookOpened.formats.html,
            hrefList = extractHrefTable(state.currentParsedBook),
        )
        state = state.copy(
            currentBookOpened = state.gBooks[bookIndex],
            myComposable = {
                HrefTable(
                    hrefLinks = hrefLinks,
                    html = state.currentParsedBook
                )

                ChapterContent(
                    chapterData = ChapterData(title = state.currentBookOpened.title)
                )
            }
        )
    }


    fun fetchContentByUrl(url: String){
        viewModelScope.launch {
            try {
                Log.d("ddd", "PROC: HTML retrieve")
                val content = withContext(Dispatchers.IO) {
                    repo.getBookContentByUrl(url)
                }
                if (content != null) {
                    Log.d("ddd", "SUCCESS: \n${extractHrefTable(content)}")
                    state = state.copy(
                        currentParsedBook = content
                    )
                }

            }catch (e: Exception){
                Log.d("ddd", "ERROR: HTML retrieve - $e")
                state = state.copy(
                    error = e.message
                )
            }
        }
    }
}

class GBookLoaderViewModel(
    currentBook: GBook
) : ViewModel(){
    private val repo = Parser()
    var state by mutableStateOf(BookLoaderState(currentBookOpened = currentBook))

    private val pagination = ContentPaginationFactory(
        initialPage = state.page,
        onLoadUpdated = {
            state = state.copy(
                isLoading = it
            )
        },
        onRequest = { nextChapterIndex ->
            val chapterContent = extractASection(
                html = state.currentParsedBook,
                sectionId = state.hrefElements[nextChapterIndex].hrefLink
            )
            state = state.copy(
                chapters = state.chapters + chapterContent,
                page = nextChapterIndex+1,
            )

        },
        getNextKey = {
            state.page + 1

        },
        onSuccess = {_, newChapter ->
            state = state.copy(
                page = newChapter,
                endReached = state.page == 100,
            )
        },
        onError = {
            state = state.copy(error = it?.localizedMessage)
        }
    )

    private fun updateCurrentOpenedBook(){
        fetchContentByUrl(state.currentBookOpened.formats.html)

//        val hrefLinks = BookHrefLinks(
//            htmlUrl = state.currentBookOpened.formats.html,
//            hrefList = extractHrefTable(state.currentParsedBook),
//        )
//        state = state.copy(
//            myComposable = {
//                HrefTable(
//                    hrefLinks = hrefLinks,
//                    html = state.currentParsedBook
//                )
//
//                ChapterContent(
//                    chapterData = ChapterData(title = state.currentBookOpened.title)
//                )
//            }
//        )
    }

    private fun fetchContentByUrl(url: String){
        viewModelScope.launch {
            try {
                Log.d("ddd", "PROC: HTML retrieve")
                val content = repo.parseHTMLByUrl(url).body()

                if (content != null) {
                    Log.d("ddd", "SUCCESS: \n${extractHrefTable(content)}")
                    state = state.copy(
                        currentParsedBook = content
                    )
                }

            }catch (e: Exception){
                Log.d("ddd", "ERROR: HTML retrieve - $e")
                state = state.copy(
                    error = e.message
                )
            }
            val hrefLinks = BookHrefLinks(
                htmlUrl = state.currentBookOpened.formats.html,
                hrefList = extractHrefTable(state.currentParsedBook),
            )
            state = state.copy(
                myComposable = {
                    HrefTable(
                        hrefLinks = hrefLinks,
                        html = state.currentParsedBook
                    )

                    ChapterContent(
                        chapterData = ChapterData(title = state.currentBookOpened.title)
                    )
                }
            )
        }
    }

    init {
        updateCurrentOpenedBook()
        if (state.currentParsedBook == ""){
            loadNextItems()
        }
    }

    private fun loadNextItems() {
        viewModelScope.launch {
            pagination.loadNextPage()
        }
    }
}

data class ScreenState(
    val gBooks: List<GBook> = emptyList(),
    val page:Int = 1,
    val endReached: Boolean = false,
    val error: String? = null,
    val isLoading: Boolean = false,

    val currentBookOpened: GBook = GBook(title = "Non-available"),
    val currentParsedBook: String = "",
    val myComposable: @Composable () -> Unit = {}
)

data class BookLoaderState(
    val hrefElements: List<hrefElement> = emptyList(),
    val chapters: List<ChapterData> = emptyList(),

    val page:Int = 0,
    val endReached: Boolean = false,
    val error: String? = null,
    val isLoading: Boolean = false,

    val currentParsedBook: String = "",
    val currentBookOpened: GBook = GBook(title = "Non-available"),
    val myComposable: @Composable () -> Unit = {}
)