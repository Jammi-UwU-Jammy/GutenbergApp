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
import kotlinx.coroutines.launch


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
                endReached = state.page == 3
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
            Log.d("ddd", state.page.toString())
            pagination.loadNextPage()
        }
    }

    fun getDetailsById(){
        viewModelScope.launch {
            try {
                val response = repo.getBookDetailsById(id=id)
                if (response.isSuccessful){
                    state = state.copy(
                        bookDetails = response.body()!!
                    )
                }

            }catch (e: Exception){
                state = state.copy(
                    error = e.message
                )
            }
        }
    }
}


data class ScreenState(
    val gBooks: List<GBook> = emptyList(),
    val page:Int = 1,
    val bookDetails: GBook = GBook(),
    val endReached: Boolean = false,
    val error: String? = null,
    val isLoading: Boolean = false
)