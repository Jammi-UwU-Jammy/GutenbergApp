package com.vivich.starlitapp.viewModels

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

    fun getBookContentPlain(url: String){
        viewModelScope.launch {
            try {
                val call = repo.getBookPlainTextByUrl(url)
                call.enqueue(object : Callback<String>{
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if (response.isSuccessful && response.body() != null) {

                        }
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })

            }catch (e: Exception){
                state = state.copy(
                    error = e.message
                )
            }
        }
    }
    fun updateBrightness( brightness: Float ){
        viewModelScope.launch {
            state = state.copy(
                brightness = brightness
            )
        }
    }
}


data class ScreenState(
    val gBooks: List<GBook> = emptyList(),
    val page:Int = 1,
    val bookDetails: GBook = GBook(),
    val endReached: Boolean = false,
    val error: String? = null,
    val isLoading: Boolean = false,
    val brightness: Float = 1f
)