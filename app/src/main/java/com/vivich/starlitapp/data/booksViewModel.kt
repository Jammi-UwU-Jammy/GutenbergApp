package com.vivich.starlitapp.data

import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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