package com.vivich.starlitapp.featureTesting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val apiService = RetrofitInstance.api
    val posts: MutableStateFlow<List<Post>> = MutableStateFlow(emptyList())
    fun getPosts() {

        viewModelScope.launch {
//            try {

                val response = apiService.getPosts()
                if (response.isNotEmpty()) {
                        posts.value = response
                }
//            } catch (e: Exception) {
//                Log.d("ddd", e.toString())
//                e.printStackTrace()
//            }
        }
    }
}